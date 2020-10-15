// TODO: add input validation before save

const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function saveMachineType() {
    let machineType = getMachineTypeFromInput();
    $.ajax({
        type: 'POST',
        url: 'machineTypeSave',
        data: JSON.stringify(machineType),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addMachineTypeToTable
    });
}

function addMachineTypeToTable(machineType) {
    let $row = getTableRow(machineType);
    $('#machineTypesTbody').prepend($row);
    clearEditField();
    if (!isNothingSelected()) {
        removeSelectedRow();
    }
}

function getTableRow(machineType) {
    return $(
        '<tr onclick="handleRowClick(this)">' +
        '<td>' + machineType.id + '</td>' +
        '<td>' + machineType.name + '</td>' +
        '</tr>'
    );
}

function editMachineType() {
    if (isNothingSelected()) {
        return;
    }
    clearEditField();
    let machineType = getSelectedMachineType();
    setMachineTypeToInput(machineType);
}

function setMachineTypeToInput(machineType) {
    $('#machineTypeId').val(machineType.id);
    $('#name').val(machineType.name);
}

function getSelectedMachineType() {
    return {
        id: $selectedRow.children(':nth-child(1)').html(),
        name: $selectedRow.children(':nth-child(2)').html()
    };
}

function deleteMachineType() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'machineTypeDelete',
        data: getSelectedMachineTypeId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: removeSelectedRow
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function getSelectedMachineTypeId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearEditField() {
    setMachineTypeToInput({
        id: 0,
        name: ''
    });
    $('#name').focus();
}

function getMachineTypeFromInput() {
    return {
        id: +$('#machineTypeId').val(),
        name: $('#name').val()
    };
}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}