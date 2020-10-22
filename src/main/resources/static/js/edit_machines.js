// TODO: add input validation before save
let isUpdateModeActive = false;
let $selectedRow = $('<tr>');
let $machinesTbody;
let $save;
let $edit;
let $delete;
let $machineId;
let $name;
let $machineType;

$(function () {
    $save = $('#save');
    $machinesTbody = $('#machinesTable tbody');
    $edit = $('#edit');
    $delete = $('#delete');
    $machineId = $('#machineId');
    $name = $('#name');
    $machineType = $('#machineType');
    addActionHandlers();
});

function addActionHandlers() {
    $('#clear').on('click', clearInput);
    $save.on('click', onSaveButtonClick);
    $machinesTbody.find('tr').on('click', selectRow);
    $edit.on('click', editMachine);
    $delete.on('click', deleteMachine);
}

function onSaveButtonClick() {
    let machine = getMachineFromInput();
    isUpdateModeActive ? updateMachine(machine) : saveMachine(machine);
}

function setMode(isUpdateRequired) {
    isUpdateModeActive = isUpdateRequired;
    isUpdateModeActive ? $save.html('Обновить') : $save.html('Сохранить');
    $edit.prop('disabled', isUpdateModeActive);
    $delete.prop('disabled', isUpdateModeActive);
}

function saveMachine(machineToSave) {
    $.ajax({
        type: 'POST',
        url: 'machineSave',
        data: JSON.stringify(machineToSave),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (savedMachine) {
            let wasNotSaved = savedMachine.id === 0;
            if (wasNotSaved) {
                alert(`Невозможно добавить!\n"${savedMachine.name}" уже существует!`);
                return;
            }
            addRowToTable(savedMachine);
        }
    });
}

function updateMachine(machineToUpdate) {
    $.ajax({
        type: 'POST',
        url: 'machineUpdate',
        data: JSON.stringify(machineToUpdate),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (wasUpdated) {
            if (!wasUpdated) {
                alert(`Невозможно обновить!\nСтанок "${machineToUpdate.name}" уже кто-то подло удалил.`);
                return;
            }
            removeSelectedRow();
            addRowToTable(machineToUpdate);
            setMode(false);
        }
    });
}

function addRowToTable(machine) {
    let $row = getTableRow(machine);
    $machinesTbody.prepend($row);
    clearInput();
}

function getTableRow(machine) {
    let $row = $(
        '<tr>' +
        '<td>' + machine.id + '</td>' +
        '<td>' + machine.name + '</td>' +
        '<td>' + machine.machineType.id + '</td>' +
        '<td>' + machine.machineType.name + '</td>' +
        '</tr>'
    );
    $row.on('click', selectRow);
    $row.addClass(CHANGED_TEXT_CLASS);
    return $row;
}

function editMachine() {
    if (isNothingSelected()) {
        return;
    }
    clearInput();
    let machine = getSelectedMachine();
    setMachineToInput(machine);
    setMode(true);
}

function setMachineToInput(machine) {
    $machineId.val(machine.id);
    $name.val(machine.name);
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
        success: function (wasDeleted) {
            if (wasDeleted) {
                removeSelectedRow();
            } else {
                alert('Не удалось удалить!\nСтанок используется или был удален ранее.')
            }
        }
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
    return $selectedRow.is(':empty');
}

function clearInput() {
    $machineId.val(0);
    $name.val('');
    $machineType.find('option:first').prop('selected', true);
    setMode(false);
    $name.focus();
}

function getMachineFromInput() {
    return {
        id: +$machineId.val(),
        name: $name.val(),
        machineType: {
            id: $machineType.val(),
            name: $machineType.find('option:selected').html()
        }
    };
}

function selectRow() {
    if (isUpdateModeActive) {
        return;
    }
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(this);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}