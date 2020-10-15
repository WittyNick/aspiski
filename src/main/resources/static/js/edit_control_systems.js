// TODO: add input validation before save

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

/*
check if (!isNothingSelected()) { removeSelectedRow() }
- true - when we update selected row (existing control system).
- false - when we save new control system.
 */
function addControlSystemToTable(controlSystem) {
    let $row = getTableRow(controlSystem);
    $('#controlSystemsTbody').prepend($row);
    clearEditField();
    if (!isNothingSelected()) {
        removeSelectedRow();
    }
}

/*
Another way to add row click handler:
$row.on('click', function () {
    handleRowClick(this);
});
 */
function getTableRow(controlSystem) {
    return $(
        '<tr onclick="handleRowClick(this)">' +
            '<td>' + controlSystem.id + '</td>' +
            '<td>' + controlSystem.name + '</td>' +
        '</tr>'
    );
}

function editControlSystem() {
    if (isNothingSelected()) {
        return;
    }
    clearEditField();
    let controlSystem = getSelectedControlSystem();
    setControlSystemToInput(controlSystem);
}

function setControlSystemToInput(controlSystem) {
    $('#controlSystemId').val(controlSystem.id);
    $('#name').val(controlSystem.name);
}

function getSelectedControlSystem() {
    return {
        id: $selectedRow.children(':nth-child(1)').html(),
        name: $selectedRow.children(':nth-child(2)').html()
    };
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

function getSelectedControlSystemId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearEditField() {
    setControlSystemToInput({
        id: 0,
        name: ''
    });
    $('#name').focus();
}

function getControlSystemFromInput() {
    return {
        id: +$('#controlSystemId').val(),
        name: $('#name').val()
    };
}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}