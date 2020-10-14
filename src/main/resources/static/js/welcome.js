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
    let isCheckboxChecked = $filterCheckbox.is(':checked');
    let filterPart = $inputPart.val().toLowerCase();
    let filterMachine = +$inputMachine.val();
    let filterSystem = +$inputSystem.val();
    let filterProgram = $inputProgram.val().toLowerCase();
    let filterWorkshop = +$inputWorkshop.val();

    let part = $tableRow.children(':eq(1)').html().toLowerCase();
    let machineId = +$tableRow.children(':eq(3)').html();
    let systemId = +$tableRow.children(':eq(5)').html();
    let program = $tableRow.children(':eq(7)').html().toLowerCase();
    let workshopId = +$tableRow.children(':eq(8)').html();

    let partCompareIndex = part.indexOf(filterPart);
    return (isCheckboxChecked && partCompareIndex === 0 || partCompareIndex !== -1) &&
        (filterMachine === 0 || machineId === filterMachine) &&
        (filterSystem === 0 || systemId === filterSystem) &&
        program.indexOf(filterProgram) !== -1 &&
        (filterWorkshop === 0 || workshopId === filterWorkshop);
}

function handleCheckbox() {
    $inputPart.focus();
}

// ----- Form validation -----
