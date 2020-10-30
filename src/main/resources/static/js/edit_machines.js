// TODO: add input validation before save
const AJAX_SAVE_URL = 'machineSave';
const AJAX_UPDATE_URL = 'machineUpdate';
const AJAX_DELETE_URL = 'machineDelete';

let $machineType;

$(function () {
    override();
    initCommon();
    $machineType = $('#machineType');
});

function override() {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    parseToRowHtml = overrideParseToRowHtml;
    setDataToInput = overrideSetDataToInput;
    getSelectedRowData = overrideGetSelectedRowData;
    clearInput = overrideClearInput;
    resetErrors = overrideResetErrors;
    validate = overrideValidate;
    getDataFromInput = overrideGetDataFromInput;
}

function overrideDeleteErrorMsg () {
    return 'Не удалось удалить, т.к. данный станок используется.';
}

function overrideParseToRowHtml(machine) {
    return $(
        `<tr>
            <td>${machine.id}</td>
            <td>${machine.name}</td>
            <td>${machine.machineType.id}</td>
            <td>${machine.machineType.name}</td>
        </tr>`
    );
}

function overrideGetSelectedRowData() {
    return {
        id: $selectedRow.children(':nth-child(1)').html(),
        name: $selectedRow.children(':nth-child(2)').html(),
        typeId: $selectedRow.children(':nth-child(3)').html()
    };
}

function overrideClearInput() {
    $hiddenId.val(0);
    $name.val('');
    $machineType.children('option:first').prop('selected', true);
    resetErrors();
    setMode(false);
    $name.focus();
}

function overrideGetDataFromInput() {
    return {
        id: +$hiddenId.val(),
        name: $name.val().trim(),
        machineType: {
            id: +$machineType.val(),
            name: $machineType.children('option:selected').html()
        }
    };
}

function overrideSetDataToInput(machine) {
    $hiddenId.val(machine.id);
    $name.val(machine.name);
    $(`#machineType option[value=${machine.typeId}]`).prop('selected', true);
}

function overrideResetErrors() {
    $name.removeClass(INVALID_INPUT_CLASS);
    $machineType.removeClass(INVALID_INPUT_CLASS);
}

function overrideValidate(machineFromInput) {
    let isValid = true;
    if (!machineFromInput.name) {
        $name.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $name.removeClass(INVALID_INPUT_CLASS);
    }
    if (machineFromInput.machineType.id === 0) {
        $machineType.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $machineType.removeClass(INVALID_INPUT_CLASS);
    }
    if (!isUpdateModeActive) {
        setTimeout(resetErrors, 1000);
    }
    return isValid;
}