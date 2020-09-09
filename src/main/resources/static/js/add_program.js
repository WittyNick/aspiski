$doc.ready(function () {
    let today = new Date().toISOString().split('T')[0];
    $('#date').val(today);
});

function goWelcomePage() {
    location.href='/';
}


// TODO: needs to be checked
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

// TODO: change stub method
function saveProgram() {
    let program = getProgram();
    let jsonProgram = JSON.stringify(program);
    alert(jsonProgram);
}