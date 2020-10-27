// TODO: add input validation before save
const AJAX_SAVE_URL = 'machineTypeSave';
const AJAX_UPDATE_URL = 'machineTypeUpdate';
const AJAX_DELETE_URL = 'machineTypeDelete';

$(function () {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    initCommon();
});

function overrideDeleteErrorMsg () {
    return 'Не удалось удалить, т.к. данный тип станка используется.';
}