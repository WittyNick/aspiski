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
    setTableRowSelector();
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    parseToRowHtml = overrideParseToRowHtml;
    setDataToInput = overrideSetDataToInput;
    getSelectedRowData = overrideGetSelectedRowData;
    clearInput = overrideClearInput;
    resetErrors = overrideResetErrors;
    validate = overrideValidate;
    getDataFromInput = overrideGetDataFromInput;
}

function setTableRowSelector() {
    tableRowSelector = {
        id: ':nth-child(1)',
        name: ':nth-child(2)',
        typeId: ':nth-child(3)',
        disabled: ':nth-child(4)'
    }
}

function overrideDeleteErrorMsg () {
    return 'Не удалось удалить, т.к. данный станок используется.';
}

function overrideParseToRowHtml(machine) {
    let symbol = machine.disabled ? '&#x2611;': '&#x2610;';
    return $(
        `<tr>
            <td>${machine.id}</td>
            <td>${machine.name}</td>
            <td>${machine.machineType.id}</td>
            <td>${machine.machineType.name}</td>
            <td>${machine.disabled}</td>
            <td>${symbol}</td>
        </tr>`
    );
}

function overrideGetSelectedRowData() {
    return {
        id: $selectedRow.children(tableRowSelector.id).html(),
        name: $selectedRow.children(tableRowSelector.name).html(),
        disabled: bool($selectedRow.children(tableRowSelector.disabled).html()),
        typeId: $selectedRow.children(tableRowSelector.typeId).html()
    };
}

function overrideClearInput() {
    $hiddenId.val(0);
    $name.val('');
    $disabledCheckbox.prop('checked', false)
    $machineType.children('option:first').prop('selected', true);
    resetErrors();
    setMode(false);
    $name.focus();
}

function overrideGetDataFromInput() {
    return {
        id: +$hiddenId.val(),
        name: $name.val().trim(),
        disabled: $disabledCheckbox.is(':checked'),
        machineType: {
            id: +$machineType.val(),
            name: $machineType.children('option:selected').html() // use this field when set to table
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
    resetErrors();
    if (!machineFromInput.name) {
        $name.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (machineFromInput.machineType.id === 0) {
        $machineType.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (!isValid && !isUpdateModeActive) {
        setTimeout(resetErrors, 1000);
    }
    return isValid;
}