const CHECK_IMG = `<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check2" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
    <path fill-rule="evenodd" d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
</svg>`;

let tableRowSelector = {
    id: ':nth-child(1)',
    name: ':nth-child(2)',
    disabled: ':nth-child(3)'
};

let isUpdateModeActive = false,
    $selectedRow = null,
    $name,
    $disabledCheckbox,
    $saveBtn,
    $editBtn,
    $deleteBtn,
    $mainTbody,
    $hiddenId;

function initCommon() {
    $name = $('#name');
    $disabledCheckbox = $('#disableCheckbox');
    $saveBtn = $('#saveBtn');
    $editBtn = $('#editBtn');
    $deleteBtn = $('#deleteBtn');
    $mainTbody = $('#mainTable tbody');
    $hiddenId = $('#hiddenId');
    addCommonHandlers();
}

function addCommonHandlers() {
    $mainTbody.children('tr').on('click', selectRow);
    $saveBtn.on('click', saveButtonHandler);
    $('#clearBtn').on('click', clearInput);
    $editBtn.on('click', editHandler);
    $deleteBtn.on('click', deleteButtonHandler);
    $(document).on('keydown', keyPressHandler);
}

function saveButtonHandler() {
    let data = getDataFromInput();
    let isDataValid = validate(data);
    if (!isDataValid) {
        return;
    }
    isUpdateModeActive ? updateEntity(data) : saveEntity(data);
}

function saveEntity(entity) {
    $.ajax({
        type: 'POST',
        url: AJAX_SAVE_URL,
        data: JSON.stringify(entity),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (savedEntity) {
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
        success: function (wasUpdated) {
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

function deleteEntity(stringId) {
    $.ajax({
        type: 'POST',
        url: AJAX_DELETE_URL,
        data: stringId, // String
        contentType: 'text/plain; charset=UTF-8',
        dataType: 'json',
        success: function (wasDeleted) {
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
            break;
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
let deleteButtonHandler = function () {
    if (isNothingSelected()) {
        return;
    }
    let rowData = getSelectedRowData();
    if (confirm(`Удалить "${rowData.name}"?`)) {
        deleteEntity(rowData.id);
    }
}

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
    let symbol = data.disabled ? CHECK_IMG : '';
    return $(
        `<tr>
            <td>${data.id}</td>
            <td>${data.name}</td>
            <td>${data.disabled}</td>
            <td>${symbol}</td>
        </tr>`
    );
}

let getSelectedRowData = function () {
    return {
        id: $selectedRow.children(tableRowSelector.id).html(),
        name: $selectedRow.children(tableRowSelector.name).html(),
        disabled: bool($selectedRow.children(tableRowSelector.disabled).html())
    };
}

let clearInput = function () {
    setDataToInput({
        id: 0,
        name: '',
        disabled: false
    });
    resetErrors();
    setMode(false);
    $name.focus();
}

let resetErrors = function () {
    $name.removeClass(INVALID_INPUT_CLASS);
}

let getDataFromInput = function () {
    return {
        id: +$hiddenId.val(),
        name: $name.val().trim(),
        disabled: $disabledCheckbox.is(':checked')
    };
}

let setDataToInput = function (data) {
    $hiddenId.val(data.id);
    $name.val(data.name);
    $disabledCheckbox.prop('checked', data.disabled);
}

let validate = function (dataFromInput) {
    if (!!dataFromInput.name) {
        resetErrors();
        return true;
    }
    $name.addClass(INVALID_INPUT_CLASS);
    if (!isUpdateModeActive) {
        setTimeout(resetErrors, 1000);
    }
    return false;
}
