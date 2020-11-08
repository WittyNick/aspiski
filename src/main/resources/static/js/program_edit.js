const AJAX_URL = 'updateProgram';

$(function () {
    resetErrors = overrideResetErrors;
    validate = overrideValidate;
    initProgramCommon();
});

function getErrorMessage() {
    return 'Невозможно обновить программу!';
}

function overrideResetErrors() {
    $partNumber.removeClass(INVALID_INPUT_CLASS);
    $programNumber.removeClass(INVALID_INPUT_CLASS);
    $date.removeClass(INVALID_INPUT_CLASS);
    $position.removeClass(INVALID_INPUT_CLASS);
}

function overrideValidate(program) {
    let isValid = true;
    resetErrors();
    if (!program.partNumber) {
        $partNumber.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (!program.programNumber) {
        $programNumber.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (!program.date) {
        $date.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (!program.position) {
        $position.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    return isValid;
}