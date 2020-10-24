// TODO: add input validation before save
let isUpdateModeActive = false;
let $selectedRow = null;
let $save;
let $edit;
let $delete;
let $controlSystemsTbody;
let $controlSystemId;
let $name;

$(function () {
    $save = $('#save');
    $edit = $('#edit');
    $delete = $('#delete');
    $controlSystemsTbody = $('#controlSystemsTable tbody');
    $controlSystemId = $('#controlSystemId');
    $name = $('#name');
    addActionHandlers();
});

function addActionHandlers() {
    $controlSystemsTbody.find('tr').on('click', selectRow);
    $save.on('click', onSaveButtonClick);
    $('#clear').on('click', clearInput);
    $edit.on('click', editControlSystem);
    $delete.on('click', deleteControlSystem);
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
    let controlSystem = getControlSystemFromInput();
    isUpdateModeActive ? updateControlSystem(controlSystem) : saveControlSystem(controlSystem);
}

function setMode(isUpdateRequired) {
    isUpdateModeActive = isUpdateRequired;
    isUpdateModeActive ? $save.html('Обновить') : $save.html('Сохранить');
    $edit.prop('disabled', isUpdateModeActive);
    $delete.prop('disabled', isUpdateModeActive);
}

function saveControlSystem(controlSystemToSave) {
    $.ajax({
        type: 'POST',
        url: 'controlSystemSave',
        data: JSON.stringify(controlSystemToSave),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (savedControlSystem) {
            let wasNotSaved = savedControlSystem.id === 0;
            if (wasNotSaved) {
                alert(`Невозможно добавить.\n"${savedControlSystem.name}" уже существует!`);
                return;
            }
            addRowToTable(savedControlSystem);
        }
    });
}

function updateControlSystem(controlSystemToUpdate) {
    $.ajax({
        type: 'POST',
        url: 'controlSystemUpdate',
        data: JSON.stringify(controlSystemToUpdate),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (wasUpdated) {
            if (!wasUpdated) {
                alert(`Невозможно обновить!\n"${controlSystemToUpdate.name}" отсутствует в базе.`);
                return;
            }
            removeSelectedRow();
            addRowToTable(controlSystemToUpdate);
            setMode(false);
        }
    });
}

function addRowToTable(controlSystem) {
    let $row = getTableRow(controlSystem);
    $controlSystemsTbody.prepend($row);
    clearInput();
}

function getTableRow(controlSystem) {
    let $row = $(
        '<tr>' +
            '<td>' + controlSystem.id + '</td>' +
            '<td>' + controlSystem.name + '</td>' +
        '</tr>'
    );
    $row.on('click', selectRow);
    $row.addClass(CHANGED_TEXT_CLASS);
    return $row;
}

function editControlSystem() {
    if (isNothingSelected()) {
        return;
    }
    clearInput();
    let controlSystem = getSelectedControlSystem();
    setControlSystemToInput(controlSystem);
    setMode(true);
}

function setControlSystemToInput(controlSystem) {
    $controlSystemId.val(controlSystem.id);
    $name.val(controlSystem.name);
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
        success: function (wasDeleted) {
            if (wasDeleted) {
                removeSelectedRow();
            } else {
                alert('Не удалось удалить, т.к. данная систему ЧПУ используется.')
            }
        }
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = null;
}

function getSelectedControlSystemId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return $selectedRow === null;
}

function clearInput() {
    setControlSystemToInput({
        id: 0,
        name: ''
    });
    setMode(false);
    $name.focus();
}

function getControlSystemFromInput() {
    return {
        id: +$controlSystemId.val(),
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