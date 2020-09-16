function goWelcomePage() {
    $(location).prop('href', '/');
}

function saveControlSystem() {
    let controlSystem = getControlSystem();
    $.ajax({
        type: 'POST',
        url: 'controlSystemSave',
        data: JSON.stringify(controlSystem),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addControlSystemToTable
    });
}

function addControlSystemToTable(controlSystem) {
    console.log('control system: id=' + controlSystem.id + ', name=' + controlSystem.name);
    clearEditField();



}

function editControlSystem() {

}

function deleteControlSystem() {

}

function clearEditField() {
    $('#controlSystemId').val('0');
    $('#name').val('');
}

function getControlSystem() {
    return {
        'id': +$('#controlSystemId').val(),
        'name': $('#name').val()
    };
}