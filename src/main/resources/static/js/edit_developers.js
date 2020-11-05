const AJAX_SAVE_URL = 'developerSave';
const AJAX_UPDATE_URL = 'developerUpdate';
const AJAX_DELETE_URL = 'developerDelete';

$(function () {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    initCommon();
});

function overrideDeleteErrorMsg () {
    return 'Не удалось удалить, т.к. данный разразобчик используется.';
}