// TODO: add input validation before save
let isUpdateModeActive = false;
let $selectedRow = $('<tr>');
let $developersTbody;
let $save;
let $edit;
let $delete;
let $developerId;
let $name;

$(function () {
    $save = $('#save');
    $edit = $('#edit');
    $delete = $('#delete');
    $developersTbody = $('#developersTable tbody');
    $developerId = $('#developerId');
    $name = $('#name');
    addActionHandlers();
});

function addActionHandlers() {
    $('#clear').on('click', clearInput);
    $save.on('click', onSaveButtonClick);
    $edit.on('click', editDeveloper);
    $delete.on('click', onDeleteClick);
    $developersTbody.find('tr').on('click', selectRow);
}

function onSaveButtonClick() {
    let developer = getDeveloperFromInput();
    isUpdateModeActive ? updateDeveloper(developer) : saveDeveloper(developer);
}

function setMode(isUpdateRequired) {
    isUpdateModeActive = isUpdateRequired;
    isUpdateModeActive ? $save.html('Обновить') : $save.html('Сохранить');
    $edit.prop('disabled', isUpdateModeActive);
    $delete.prop('disabled', isUpdateModeActive);
}

function saveDeveloper(developerToSave) {
    $.ajax({
        type: 'POST',
        url: 'developerSave',
        data: JSON.stringify(developerToSave),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (savedDeveloper) {
            let wasNotSaved = savedDeveloper.id === 0;
            if (wasNotSaved) {
                alert(`Невозможно добавить!\n"${savedDeveloper.name}" уже существует`);
                return;
            }
            addRowToTable(savedDeveloper);
        }
    });
}

function updateDeveloper(developerToUpdate) {
    $.ajax({
        type: 'POST',
        url: 'developerUpdate',
        data: JSON.stringify(developerToUpdate),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (wasUpdated) {
            if (!wasUpdated) {
                alert(`Невозможно обновить!\n"${developerToUpdate.name}" отсутствует в базе.`);
                return;
            }
            removeSelectedRow();
            addRowToTable(developerToUpdate);
            setMode(false);
        }
    });
}

function addRowToTable(developer) {
    let $row = getTableRow(developer);
    $developersTbody.prepend($row);
    clearInput();
}

function getTableRow(developer) {
    let $row = $(
        '<tr>' +
        '<td>' + developer.id + '</td>' +
        '<td>' + developer.name + '</td>' +
        '</tr>'
    );
    $row.on('click', selectRow);
    $row.addClass(CHANGED_TEXT_CLASS);
    return $row;
}

function editDeveloper() {
    if (isNothingSelected()) {
        return;
    }
    clearInput();
    let developer = getSelectedDeveloper();
    setDeveloperToInput(developer);
    setMode(true);
}

function setDeveloperToInput(developer) {
    $developerId.val(developer.id);
    $name.val(developer.name);
}

function getSelectedDeveloper() {
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
        url: 'developerDelete',
        data: getSelectedDeveloperId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: function (wasDeleted) {
            if (wasDeleted) {
                removeSelectedRow();
            } else {
                alert('Не удалось удалить, т.к. данный разработчик используется.');
            }
        }
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function getSelectedDeveloperId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearInput() {
    setDeveloperToInput({
        id: 0,
        name: ''
    });
    setMode(false);
    $name.focus();
}

function getDeveloperFromInput() {
    return {
        id: +$developerId.val(),
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