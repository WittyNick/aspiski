// TODO: add input validation before save
const AJAX_SAVE_URL = 'workshopSave';
const AJAX_UPDATE_URL = 'workshopUpdate';
const AJAX_DELETE_URL = 'workshopDelete';

$(function () {
    initCommon();
    getDeleteErrorMsg = overrideDeleteErrorMsg;
});

function overrideDeleteErrorMsg () {
    return 'Не удалось удалить, т.к. данный цех используется.';
}