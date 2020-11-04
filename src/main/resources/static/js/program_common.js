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

function goWelcomePage() {
    $(location).prop('href', '/');
}

function getProgram() {
    return {
        id: +$programId.val(),
        partNumber: $partNumber.val().trim(),
        programNumber: $programNumber.val().trim(),
        position: +$position.val(),
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
            if (isDone) {
                goWelcomePage();
            } else {
                alert(getErrorMessage());
            }
        }
    });
}

let validate = function (program) {
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
    if (program.developer.id < 1) {
        $developer.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $developer.removeClass(INVALID_INPUT_CLASS);
    }
    if (program.machine.id < 1) {
        $machine.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $machine.removeClass(INVALID_INPUT_CLASS);
    }
    if (program.workshop.id < 1) {
        $workshop.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $workshop.removeClass(INVALID_INPUT_CLASS);
    }
    if (program.controlSystem.id < 1) {
        $controlSystem.addClass(INVALID_INPUT_CLASS);
        isValid = false;
    } else {
        $controlSystem.removeClass(INVALID_INPUT_CLASS);
    }
    return isValid;
}