const AJAX_SAVE_URL = 'saveControlSystem';
const AJAX_UPDATE_URL = 'updateControlSystem';
const AJAX_DELETE_URL = 'deleteControlSystem';

$(function () {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    initCommon();
});

function overrideDeleteErrorMsg() {
    return 'Не удалось удалить, т.к. данная система ЧПУ используется.';
}