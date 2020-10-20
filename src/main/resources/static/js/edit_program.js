$(function() {
    $('#cancel').on('click', goWelcomePage);
    $('#update').on('click', updateProgram);
});

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

function updateProgram() {
    let program = getProgram();
    $.ajax({
        type: 'POST',
        url: 'programUpdate',
        data: JSON.stringify(program),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: onUpdateAction
    });
}

function onUpdateAction(wasUpdated) {
    if (wasUpdated) {
        goWelcomePage();
    } else {
        alert('Невозможно обновить программу!');
    }
}