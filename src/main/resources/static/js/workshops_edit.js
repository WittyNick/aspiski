const AJAX_SAVE_URL = 'saveWorkshop';
const AJAX_UPDATE_URL = 'updateWorkshop';
const AJAX_DELETE_URL = 'deleteWorkshop';

$(function () {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    initCommon();
});

function overrideDeleteErrorMsg() {
    return 'Не удалось удалить, т.к. данный цех используется.';
}