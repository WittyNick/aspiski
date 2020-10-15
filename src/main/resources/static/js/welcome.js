const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

let $programsTbody;
let $filterCheckbox;
let $inputPart;
let $inputMachine;
let $inputSystem;
let $inputProgram;
let $inputWorkshop;

$(function () {
    $programsTbody = $('#programsTable tbody');
    $filterCheckbox = $('#orderCheckbox');
    $inputPart = $('#part');
    $inputMachine = $('#machine');
    $inputSystem = $('#controlSystem');
    $inputProgram = $('#programNumber');
    $inputWorkshop = $('#workshop');
    addActionListeners();
});

function addActionListeners() {
    $('#filter').on('input', filterTable);
    $filterCheckbox.on('input', handleCheckbox);
    $('#info').on('click', showInfo);
    $('#edit').on('click', editCNCProgram);
    $('#delete').on('click', deleteCNCProgram);
    $programsTbody.find('tr').on('click', handleRowClick);
}

function showInfo() {
    if (isNothingSelected()) {
        return;
    }
    let info = $selectedRow.children(':eq(12)').html();
    if (isStringEmpty(info)) {
        info = '&#x2718; примечания нет';
    }
    $('#modalBodyInfo').html(info);
    $('#infoModal').modal();
}

function editCNCProgram() {
    if (isNothingSelected()) {
        return;
    }
    let id = $selectedRow.children(':first-child').html();
    $('#programId').val(id);
    $('#editProgramById').submit();
}

function deleteCNCProgram() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'programDelete',
        data: getSelectedProgramId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: removeSelectedRow
    });
}

function getSelectedProgramId() {
    return $selectedRow.children(':first-child').html();
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function handleRowClick() {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(this);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}

function unselectRow() {
    if (!isNothingSelected()) {
        $selectedRow.removeClass(SELECTED_ROW_CLASS);
        $selectedRow = $('<tr>');
    }
}

function isNothingSelected() {
    return $selectedRow.is(':empty');
}

// ----- Search filter -----

function filterTable() {
    unselectRow();
    $programsTbody.find('tr').each(function (i, row) {
        let $row = $(row);
        isMatches($row) ? $row.removeClass('hidden') : $row.addClass('hidden');
    });
}

function isMatches($tableRow) {
    let filter = parseFilterOptions();
    let row = parseTableRowOptions($tableRow);

    let partCompareIndex = row.part.indexOf(filter.part);
    return (filter.isExactSearchChecked && partCompareIndex === 0 || partCompareIndex !== -1) &&
        (filter.machineId === 0 || row.machineId === filter.machineId) &&
        (filter.systemId === 0 || row.systemId === filter.systemId) &&
        row.program.indexOf(filter.program) !== -1 &&
        (filter.workshopId === 0 || row.workshopId === filter.workshopId);
}

function parseFilterOptions() {
    return {
        isExactSearchChecked: $filterCheckbox.is(':checked'),
        part: $inputPart.val().toLowerCase(),
        machineId: +$inputMachine.val(),
        systemId: +$inputSystem.val(),
        program: $inputProgram.val().toLowerCase(),
        workshopId: +$inputWorkshop.val()
    };
}

function parseTableRowOptions($tableRow) {
    return {
        part: $tableRow.children(':eq(1)').html().toLowerCase(),
        machineId: +$tableRow.children(':eq(3)').html(),
        systemId: +$tableRow.children(':eq(5)').html(),
        program: $tableRow.children(':eq(7)').html().toLowerCase(),
        workshopId: +$tableRow.children(':eq(8)').html()
    };
}

function handleCheckbox() {
    $inputPart.focus();
}

// ----- Form validation -----
