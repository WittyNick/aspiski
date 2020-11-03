const AJAX_LOAD_URL = 'loadPrograms';

let $fromDate,
    $toDate,
    $programsTbody,
    $total;

$(function () {
    $fromDate = $('#fromDate');
    $toDate = $('#toDate');
    $programsTbody = $('#programsTable tbody')
    $total = $('#total');
    $('#loadBtn').on('click', loadData);
});

function loadData() {
    let dateRage = getDateRage();
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
    $total.html(programs.length);
    $programsTbody.children('tr').remove();
    $(programs).each(function (i, program) {
        let $row = getRowHtml(program);
        $programsTbody.append($row);
    });
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