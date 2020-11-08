const AJAX_SAVE_URL = 'saveDeveloper';
const AJAX_UPDATE_URL = 'updateDeveloper';
const AJAX_DELETE_URL = 'deleteDeveloper';

$(function () {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    initCommon();
});

function overrideDeleteErrorMsg() {
    return 'Не удалось удалить, т.к. данный разразобчик используется.';
}