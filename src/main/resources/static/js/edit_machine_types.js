// TODO: add input validation before save
let isUpdateModeActive = false;
let $selectedRow = null;
let $machineTypesTbody;
let $save;
let $edit;
let $delete;
let $machineTypeId;
let $name;

$(function() {
    $machineTypesTbody = $('#machineTypesTable tbody');
    $save = $('#save');
    $edit = $('#edit');
    $delete = $('#delete');
    $machineTypeId = $('#machineTypeId');
    $name = $('#name');
    addActionHandlers();
});

function addActionHandlers() {
    $machineTypesTbody.find('tr').on('click', selectRow);
    $delete.on('click', onDeleteClick);
    $save.on('click', onSaveButtonClick);
    $('#clear').on('click', clearInput);
    $edit.on('click', editMachineType);
    $(document).on('keydown', keyPressHandler);
}

function keyPressHandler(event) {
    if (isNothingSelected()) {
        return;
    }
    switch (event.keyCode) {
        case 40: // down
            $selectedRow.next().click();
            break;
        case 38: // up
            $selectedRow.prev().click();
    }
}

function onSaveButtonClick() {
    let machineType = getMachineTypeFromInput();
    isUpdateModeActive ? updateMachineType(machineType) : saveMachineType(machineType);
}

function setMode(isUpdateRequired) {
    isUpdateModeActive = isUpdateRequired;
    isUpdateModeActive ? $save.html('Обновить') : $save.html('Сохранить');
    $edit.prop('disabled', isUpdateModeActive);
    $delete.prop('disabled', isUpdateModeActive);
}

function saveMachineType(machineTypeToSave) {
    $.ajax({
        type: 'POST',
        url: 'machineTypeSave',
        data: JSON.stringify(machineTypeToSave),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function(savedMachineType) {
            let wasNotSaved = savedMachineType.id === 0;
            if (wasNotSaved) {
                alert(`Невозможно добавить!\n"${savedMachineType.name}" уже существует.`);
                return;
            }
            addRowToTable(savedMachineType);
        }
    });
}

function updateMachineType(machineTypeToUpdate) {
    $.ajax({
        type: 'POST',
        url: 'machineTypeUpdate',
        data: JSON.stringify(machineTypeToUpdate),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function(wasUpdated) {
            if (!wasUpdated) {
                alert(`Невозможно обновить!\n"${machineTypeToUpdate.name}" отсутствует в базе.`);
                return;
            }
            removeSelectedRow();
            addRowToTable(machineTypeToUpdate);
            setMode(false);
        }
    });
}

function addRowToTable(machineType) {
    let $row = getTableRow(machineType);
    $machineTypesTbody.prepend($row);
    clearInput();
}

function getTableRow(machineType) {
    let $row = $(
        '<tr>' +
            '<td>' + machineType.id + '</td>' +
            '<td>' + machineType.name + '</td>' +
        '</tr>'
    );
    $row.on('click', selectRow);
    $row.addClass(CHANGED_TEXT_CLASS);
    return $row;
}

function editMachineType() {
    if (isNothingSelected()) {
        return;
    }
    clearInput();
    let machineType = getSelectedMachineType();
    setMachineTypeToInput(machineType);
    setMode(true);
}

function setMachineTypeToInput(machineType) {
    $machineTypeId.val(machineType.id);
    $name.val(machineType.name);
}

function getSelectedMachineType() {
    return {
        id: $selectedRow.children(':nth-child(1)').html(),
        name: $selectedRow.children(':nth-child(2)').html()
    };
}

function onDeleteClick() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'machineTypeDelete',
        data: getSelectedRowId(), // String
        contentType: 'text/plain; charset=UTF-8',
        dataType: 'json',
        success: function(wasDeleted) {
            if (wasDeleted) {
                removeSelectedRow();
            } else {
                alert('Не удалось удалить, т.к. данный тип станка используется.');
            }
        }
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = null;
}

function getSelectedRowId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow === null;
}

function clearInput() {
    setMachineTypeToInput({
        id: 0,
        name: ''
    });
    setMode(false);
    $name.focus();
}

function getMachineTypeFromInput() {
    return {
        id: +$machineTypeId.val(),
        name: $name.val()
    };
}

function selectRow() {
    if (isUpdateModeActive) {
        return;
    }
    if (!isNothingSelected()) {
        $selectedRow.removeClass(SELECTED_ROW_CLASS);
    }
    $selectedRow = $(this);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}