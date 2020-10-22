// TODO: add input validation before save
let isUpdateModeActive = false;
let $selectedRow = $('<tr>');
let $workshopsTbody;
let $workshopId;
let $name;
let $save;
let $edit;
let $delete;

$(function () {
    $workshopsTbody = $('#workshopsTable tbody');
    $workshopId = $('#workshopId');
    $name = $('#name');
    $save = $('#save');
    $edit = $('#edit');
    $delete = $('#delete');
    addActionHandlers();
});

function addActionHandlers() {
    $('#clear').on('click', clearInput);
    $save.on('click', onSaveButtonClick);
    $workshopsTbody.find('tr').on('click', selectRow);
    $edit.on('click', editWorkshop);
    $delete.on('click', onDeleteClick);
}

function onSaveButtonClick() {
    let workshop = getWorkshopFromInput();
    isUpdateModeActive ? updateWorkshop(workshop) : saveWorkshop(workshop);
}

function setMode(isUpdateRequired) {
    isUpdateModeActive = isUpdateRequired;
    isUpdateModeActive ? $save.html('Обновить') : $save.html('Сохранить');
    $edit.prop('disabled', isUpdateModeActive);
    $delete.prop('disabled', isUpdateModeActive);
}

function saveWorkshop(workshopToSave) {
    $.ajax({
        type: 'POST',
        url: 'workshopSave',
        data: JSON.stringify(workshopToSave),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (savedWorkshop) {
            let wasNotSaved = savedWorkshop.id === 0;
            if (wasNotSaved) {
                alert(`Невозможно добавить!\nЦех "${savedWorkshop.name}" уже существует!`);
                return;
            }
            addRowToTable(savedWorkshop);
        }
    });
}

function updateWorkshop(workshopToUpdate) {
    $.ajax({
        type: 'POST',
        url: 'workshopUpdate',
        data: JSON.stringify(workshopToUpdate),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (wasUpdated) {
            if (!wasUpdated) {
                alert(`Невозможно обновить!\nЦех "${workshopToUpdate.name}" отсутствует в базе.`);
                return;
            }
            removeSelectedRow();
            addRowToTable(workshopToUpdate);
            setMode(false);
        }
    });
}

function addRowToTable(workshop) {
    let $row = getTableRow(workshop);
    $workshopsTbody.prepend($row);
    clearInput();
}

function getTableRow(workshop) {
    let $row = $(
        '<tr>' +
        '<td>' + workshop.id + '</td>' +
        '<td>' + workshop.name + '</td>' +
        '</tr>'
    );
    $row.on('click', selectRow);
    $row.addClass(CHANGED_TEXT_CLASS);
    return $row;
}

function editWorkshop() {
    if (isNothingSelected()) {
        return;
    }
    clearInput();
    let workshop = getSelectedWorkshop();
    setWorkshopToInput(workshop);
    setMode(true);
}

function setWorkshopToInput(workshop) {
    $workshopId.val(workshop.id);
    $name.val(workshop.name);
}

function getSelectedWorkshop() {
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
        url: 'workshopDelete',
        data: getSelectedWorkshopId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: function (wasDeleted) {
            if (wasDeleted) {
                removeSelectedRow();
            } else {
                alert('Не удалось удалить, т.к. данный цех используется.');
            }
        }
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function getSelectedWorkshopId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return $selectedRow.is(':empty');
}

function clearInput() {
    setWorkshopToInput({
        id: 0,
        name: ''
    });
    setMode(false);
    $name.focus();
}

function getWorkshopFromInput() {
    return {
        id: +$workshopId.val(),
        name: $name.val()
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