const AJAX_LOAD_URL = 'getProgramsBetweenDates';

let $fromDate,
    $toDate,
    $filterDeveloper,
    $filterMachineType,
    $programsTbody,
    $tableSize;

$(function () {
    $fromDate = $('#fromDate');
    $toDate = $('#toDate');
    $filterDeveloper = $('#developer');
    $filterMachineType = $('#machineType');
    $programsTbody = $('#programsTable tbody')
    $tableSize = $('#tableSize');
    addActionHandlers();
});

function addActionHandlers() {
    $('#loadBtn').on('click', loadData);
    $('#filter').on('click', filterTable);
}

function filterTable() {
    $programsTbody.children('tr').each(function (i, row) {
        let $row = $(row);
        isMatches($row) ? $row.removeClass(HIDE_CLASS) : $row.addClass(HIDE_CLASS);
    });
}

function isMatches($tableRow) {
    let filter = parseFilterOptions();
    let row = parseTableRowOptions($tableRow);
    return (filter.machineTypeId === 0 || row.machineTypeId === filter.machineTypeId) &&
        (filter.developerId === 0 || row.developerId === filter.developerId);
}

function parseFilterOptions() {
    return {
        machineTypeId: +$filterMachineType.val(),
        developerId: +$filterDeveloper.val()
    };
}

function parseTableRowOptions($tableRow) {
    let $columns = $tableRow.children();
    return {
        machineTypeId: +$columns.eq(2).html(),
        developerId: +$columns.eq(6).html()
    };
}

function loadData() {
    let dateRage = getDateRage();
    let isValid = validate(dateRage);
    if (!isValid) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: AJAX_LOAD_URL,
        data: JSON.stringify(dateRage),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addToTable
    });
}

function getDateRage() {
    return {
        from: $fromDate.val(),
        to: $toDate.val()
    }
}

function addToTable(programs) {
    $tableSize.html(programs.length);
    $programsTbody.children('tr').remove();
    $(programs).each(function (i, program) {
        let $row = getRowHtml(program);
        $programsTbody.append($row);
    });
    filterTable();
}

function getRowHtml(program) {
    let dateRu = stringDateEnToRu(program.date);
    return $(
        `<tr>
            <td>${program.partNumber}</td>
            <td>${program.machine.name}</td>
            <td>${program.machine.machineType.name}</td>
            <td>${program.controlSystem.name}</td>
            <td>${program.programNumber}</td>
            <td>${program.workshop.name}</td>
            <td>${program.developer.name}</td>
            <td>${dateRu}</td>
        </tr>`
    );
}

function validate(dateRage) {
    let isValid = true;
    resetErrors();
    if (!dateRage.from) {
        $fromDate.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (!dateRage.to) {
        $toDate.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    setTimeout(resetErrors, 2000);
    return isValid;
}

function resetErrors() {
    $fromDate.removeClass(INVALID_INPUT_CLASS);
    $toDate.removeClass(INVALID_INPUT_CLASS);
}