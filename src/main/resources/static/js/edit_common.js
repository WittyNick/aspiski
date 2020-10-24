let isUpdateModeActive = false;
let $selectedRow = null;

let $name;
let $saveBtn;
let $editBtn;
let $deleteBtn;
let $mainTbody;
let $hiddenId;

function initCommon() {
    $name = $('#name');
    $saveBtn = $('#saveBtn');
    $editBtn = $('#editBtn');
    $deleteBtn = $('#deleteBtn');
    $mainTbody = $('#mainTable tbody');
    $hiddenId = $('#hiddenId');
    addCommonHandlers();
}

function addCommonHandlers() {
    $mainTbody.find('tr').on('click', selectRow);
    $saveBtn.on('click', saveButtonHandler);
    $('#clearBtn').on('click', clearInput);
    $editBtn.on('click', editHandler);
    $deleteBtn.on('click', deleteButtonHandler);
    $(document).on('keydown', keyPressHandler);
}

function saveButtonHandler() {
    let data = getDataFromInput();
    isUpdateModeActive ? updateEntity(data) : saveEntity(data);
}

function deleteButtonHandler() {
    let id = getSelectedRowId();
    deleteEntity(id);
}

function saveEntity(entity) {
    $.ajax({
        type: 'POST',
        url: AJAX_SAVE_URL,
        data: JSON.stringify(entity),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function(savedEntity) {
            let wasNotSaved = savedEntity.id === 0;
            if (wasNotSaved) {
                alert(getSaveErrorMsg(entity));
                return;
            }
            addRowToTable(savedEntity);
        }
    });
}

function updateEntity(entity) {
    $.ajax({
        type: 'POST',
        url: AJAX_UPDATE_URL,
        data: JSON.stringify(entity),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function(wasUpdated) {
            if (!wasUpdated) {
                alert(getUpdateErrorMsg(entity));
                return;
            }
            removeSelectedRow();
            addRowToTable(entity);
            setMode(false);
        }
    });
}

function deleteEntity() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: AJAX_DELETE_URL,
        data: getSelectedRowId(), // String
        contentType: 'text/plain; charset=UTF-8',
        dataType: 'json',
        success: function(wasDeleted) {
            if (wasDeleted) {
                removeSelectedRow();
            } else {
                alert(getDeleteErrorMsg());
            }
        }
    });
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

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = null;
}

function editHandler() {
    if (isNothingSelected()) {
        return;
    }
    clearInput();
    let data = getSelectedRowData();
    setDataToInput(data);
    setMode(true);
}

function addRowToTable(data) {
    let $row = parseToRowHtml(data);
    $row.on('click', selectRow);
    $row.addClass(CHANGED_TEXT_CLASS);
    $mainTbody.prepend($row);
    clearInput();
}

function setMode(isUpdateRequired) {
    isUpdateModeActive = isUpdateRequired;
    isUpdateModeActive ? $saveBtn.html('Обновить') : $saveBtn.html('Сохранить');
    $editBtn.prop('disabled', isUpdateModeActive);
    $deleteBtn.prop('disabled', isUpdateModeActive);
}

function isNothingSelected() {
    return $selectedRow === null;
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

// Default functions

let getSaveErrorMsg = function (entity) {
    return `Невозможно добавить!\n"${entity.name}" уже существует.`;
}

let getUpdateErrorMsg = function (entity) {
    return `Невозможно обновить!\n"${entity.name}" отсутствует в базе.`;
}

let getDeleteErrorMsg = function () {
    return 'Не удалось удалить!\nТ.к. используется.';
}

let parseToRowHtml = function (data) {
    return $(
        `<tr>
            <td>${data.id}</td>
            <td>${data.name}</td>
        </tr>`
    );
}

let getSelectedRowData = function () {
    return {
        id: $selectedRow.children(':nth-child(1)').html(),
        name: $selectedRow.children(':nth-child(2)').html()
    };
}

let getSelectedRowId = function () {
    return $selectedRow.children(':first-child').html();
}

let clearInput = function () {
    setDataToInput({
        id: 0,
        name: ''
    });
    setMode(false);
    $name.focus();
}

let getDataFromInput = function () {
    return {
        id: +$hiddenId.val(),
        name: $name.val()
    };
}

let setDataToInput = function (data) {
    $hiddenId.val(data.id);
    $name.val(data.name);
}