function goWelcomePage() {
    $(location).prop('href', '/');
}

function saveControlSystem() {
    let controlSystem = getControlSystemFromInput();
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
    let $row = getTableRow(controlSystem);
    $('#controlSystemsTbody').prepend($row);
    clearEditField();
}

function getTableRow(controlSystem) {
    let row = '<tr><td>' + controlSystem.id +'</td><td>' + controlSystem.name + '</td></tr>';
    return $(row);
}




function editControlSystem() {

}

function deleteControlSystem() {
    let controlSystem = getSelectedControlSystem();
    $.ajax({
        type: 'POST',
        url: 'controlSystemDelete',
        data: JSON.stringify(controlSystem),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addControlSystemToTable
    });
}

function getSelectedControlSystem() {

}

function clearEditField() {
    $('#controlSystemId').val('0');
    $('#name').val('');
}

function getControlSystemFromInput() {
    return {
        'id': +$('#controlSystemId').val(),
        'name': $('#name').val()
    };
}