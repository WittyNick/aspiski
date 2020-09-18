const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function saveControlSystem() {
    let controlSystem = getControlSystemFromInput();
    $.ajax({
        type: 'POST',
        url: 'controlSystemSave',
        data: JSON.stringify(controlSystem),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addControlSystemToTable
    });
}

function addControlSystemToTable(controlSystem) {
    let $row = getTableRow(controlSystem);
    $('#controlSystemsTbody').prepend($row);
    clearEditField();
}

function getTableRow(controlSystem) {
    return $(
        '<tr onclick="handleRowClick(this)">' +
            '<td>' + controlSystem.id + '</td>' +
            '<td>' + controlSystem.name + '</td>' +
        '</tr>'
    );
    // $row.on('click', function () { // variant how to add handler
    //     handleRowClick(this);
    // });
}

function editControlSystem() {

}

function deleteControlSystem() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'controlSystemDelete',
        data: getSelectedControlSystemId(), // (String) controlSystemId
        contentType: 'text/plain; charset=UTF-8',
        success: removeSelectedRow
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

// Returns id in String format
function getSelectedControlSystemId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearEditField() {
    $('#controlSystemId').val('0');
    let $name = $('#name');
    $name.val('');
    $name.focus();
}

function getControlSystemFromInput() {
    return {
        'id': +$('#controlSystemId').val(),
        'name': $('#name').val()
    };
}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}