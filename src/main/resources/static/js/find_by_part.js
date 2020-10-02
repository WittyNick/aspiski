const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function goAddProgram() {
    $(location).prop('href', '/addProgram');
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

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}