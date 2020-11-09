let $programId,
    $partNumber,
    $programNumber,
    $position,
    $workshop,
    $developer,
    $machine,
    $controlSystem,
    $date,
    $info;

function initProgramCommon() {
    $programId = $('#programId');
    $partNumber = $('#partNumber');
    $programNumber = $('#programNumber');
    $position = $('#position');
    $workshop = $('#workshop');
    $developer = $('#developer');
    $machine = $('#machine');
    $controlSystem = $('#controlSystem');
    $date = $('#date');
    $info = $('#info');
    $('#cancelBtn').on('click', goWelcomePage);
    $('#saveBtn').on('click', saveProgram);
}

function getProgram() {
    return {
        id: +$programId.val(),
        partNumber: $partNumber.val().trim(),
        programNumber: $programNumber.val().trim(),
        position: $position.val(),
        workshop: {
            id: +$workshop.val()
        },
        developer: {
            id: +$developer.val()
        },
        machine: {
            id: +$machine.val()
        },
        controlSystem: {
            id: +$controlSystem.val()
        },
        date: $date.val(), // yyyy-MM-dd
        info: $info.val()
    };
}

function saveProgram() {
    let program = getProgram();
    let isValidData = validate(program);
    if (!isValidData) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: AJAX_URL,
        data: JSON.stringify(program),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (isDone) {
            if (isDone === true) {
                goWelcomePage();
            } else {
                alert(getErrorMessage());
            }
        }
    });
}

let resetErrors = function () {
    $partNumber.removeClass(INVALID_INPUT_CLASS);
    $programNumber.removeClass(INVALID_INPUT_CLASS);
    $date.removeClass(INVALID_INPUT_CLASS);
    $position.removeClass(INVALID_INPUT_CLASS);
    $developer.removeClass(INVALID_INPUT_CLASS);
    $machine.removeClass(INVALID_INPUT_CLASS);
    $workshop.removeClass(INVALID_INPUT_CLASS);
    $controlSystem.removeClass(INVALID_INPUT_CLASS);
}

let validate = function (program) {
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
    if (program.developer.id < 1) {
        $developer.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (program.machine.id < 1) {
        $machine.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (program.workshop.id < 1) {
        $workshop.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    if (program.controlSystem.id < 1) {
        $controlSystem.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    }
    return isValid;
}