function initProgramCommon() {
    $('#cancelBtn').on('click', goWelcomePage);
    $('#saveBtn').on('click', saveProgram);
}

function goWelcomePage() {
    $(location).prop('href', '/');
}

function getProgram() {
    return {
        id: +$('#programId').val(),
        partNumber: $('#partNumber').val(),
        programNumber: $('#programNumber').val(),
        position: $('#position').val(),
        workshop: {
            id: +$('#workshop').val()
        },
        developer: {
            id: +$('#developer').val()
        },
        machine: {
            id: +$('#machine').val()
        },
        controlSystem: {
            id: +$('#controlSystem').val()
        },
        date: $('#date').val(), // yyyy-MM-dd
        info: $('#info').val()
    };
}

function saveProgram() {
    let program = getProgram();
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