const AJAX_SAVE_URL = 'workshopSave';
const AJAX_UPDATE_URL = 'workshopUpdate';
const AJAX_DELETE_URL = 'workshopDelete';

$(function () {
    getDeleteErrorMsg = overrideDeleteErrorMsg;
    initCommon();
});

function overrideDeleteErrorMsg () {
    return 'Не удалось удалить, т.к. данный цех используется.';
}