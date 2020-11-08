const AJAX_SAVE_URL = 'saveMachineType';
const AJAX_UPDATE_URL = 'updateMachineType';
const AJAX_DELETE_URL = 'deleteMachineType';

$(function () {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    initCommon();
});

function overrideDeleteErrorMsg() {
    return 'Не удалось удалить, т.к. данный тип станка используется.';
}