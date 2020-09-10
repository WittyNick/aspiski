$(document).ready(function () {
    let today = new Date().toISOString().split('T')[0];
    $('#date').val(today);
});

function goWelcomePage() {
    $(location).prop('href', '/');
}

function getProgram() {
    return {
        'id': +$('#programId').val(),
        'partNumber': $('#partNumber').val(),
        'programNumber': $('#programNumber').val(),
        'position': $('#position').val(),
        'workshop': {
            'id': +$('#workshop').val()
        },
        'developer': {
            'id': +$('#developer').val()
        },
        'machine': {
            'id': +$('#machine').val()
        },
        'controlSystem': {
            'id': +$('#controlSystem').val()
        },
        'date': $('#date').val(), // yyyy--MM-dd
        'info': $('#info').val()
    };
}

function saveProgram() {
    let program = getProgram();
    $.ajax({
        type: 'POST',
        url: 'programSave',
        data: JSON.stringify(program),
        contentType: 'application/json; charset=UTF-8',
        success: goWelcomePage
    });
}

