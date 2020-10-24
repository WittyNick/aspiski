// TODO: add input validation before save
const AJAX_SAVE_URL = 'controlSystemSave';
const AJAX_UPDATE_URL = 'controlSystemUpdate';
const AJAX_DELETE_URL = 'controlSystemDelete';

$(function () {
    initCommon();
    getDeleteErrorMsg = overrideDeleteErrorMsg;
});

function overrideDeleteErrorMsg () {
    return 'Не удалось удалить, т.к. данная система ЧПУ используется.';
}