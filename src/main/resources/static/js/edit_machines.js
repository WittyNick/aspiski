// TODO: add input validation before save

const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function saveMachine() {
    let machine = getMachineFromInput();

    console.log(machine);

    $.ajax({
        type: 'POST',
        url: 'machineSave',
        data: JSON.stringify(machine),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addMachineToTable
    });
}

function addMachineToTable(machine) {
    let $row = getTableRow(machine);
    $('#machinesTbody').prepend($row);
    clearAllFields();
    if (!isNothingSelected()) {
        removeSelectedRow();
    }
}

function getTableRow(machine) {
    return $(
        '<tr onclick="handleRowClick(this)">' +
        '<td>' + machine.id + '</td>' +
        '<td>' + machine.name + '</td>' +
        '<td>' + machine.machineType.id + '</td>' +
        '<td>' + machine.machineType.name + '</td>' +
        '</tr>'
    );
}

function editMachine() {
    if (isNothingSelected()) {
        return;
    }
    clearAllFields();
    let machine = getSelectedMachine();
    setMachineToInput(machine);
}

function setMachineToInput(machine) {
    $('#machineId').val(machine.id);
    $('#name').val(machine.name);
    $('#machineType option[value=' + machine.typeId + ']').prop('selected', true);
}

function getSelectedMachine() {
    return {
        id: $selectedRow.children(':nth-child(1)').html(),
        name: $selectedRow.children(':nth-child(2)').html(),
        typeId: $selectedRow.children(':nth-child(3)').html()
    };
}

function deleteMachine() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'machineDelete',
        data: getSelectedMachineId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: removeSelectedRow
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function getSelectedMachineId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearEditField() {
    $('#machineId').val(0);
    let $name = $('#name');
    $name.val('');
    $name.focus();
}

function clearAllFields() {
    clearEditField();
    $('#machineType option:first').prop('selected', true);
}

function getMachineFromInput() {
    return {
        id: +$('#machineId').val(),
        name: $('#name').val(),
        machineType: {
            id: $('#machineType').val(),
            name: $('#machineType option:selected').html()
        }
    };
}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}