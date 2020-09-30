const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function goAddProgram() {
    $(location).prop('href', '/addProgram');
}

function editCNCProgram() {

}

function deleteCNCProgram() {

}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}