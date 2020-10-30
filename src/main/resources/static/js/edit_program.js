const AJAX_URL = 'programUpdate';

$(function () {
    validate = overrideValidate;
    initProgramCommon();
});

function getErrorMessage() {
    return 'Невозможно обновить программу!';
}

function overrideValidate(program) {
    let isValid = true;
    if (!program.partNumber) {
        $partNumber.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $partNumber.removeClass(INVALID_INPUT_CLASS);
    }
    if (!program.programNumber) {
        $programNumber.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $programNumber.removeClass(INVALID_INPUT_CLASS);
    }
    if (!program.date) {
        $date.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $date.removeClass(INVALID_INPUT_CLASS);
    }
    if (program.position < 1) {
        $position.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $position.removeClass(INVALID_INPUT_CLASS);
    }
    return isValid;
}