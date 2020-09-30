// TODO: add input validation before save

const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function saveWorkshop() {
    let workshop = getWorkshopFromInput();
    $.ajax({
        type: 'POST',
        url: 'workshopSave',
        data: JSON.stringify(workshop),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addWorkshopToTable
    });
}

function addWorkshopToTable(workshop) {
    let $row = getTableRow(workshop);
    $('#workshopsTbody').prepend($row);
    clearEditField();
    if (!isNothingSelected()) {
        removeSelectedRow();
    }
}

function getTableRow(workshop) {
    return $(
        '<tr onclick="handleRowClick(this)">' +
        '<td>' + workshop.id + '</td>' +
        '<td>' + workshop.name + '</td>' +
        '</tr>'
    );
}

function editWorkshop() {
    if (isNothingSelected()) {
        return;
    }
    clearEditField();
    let workshop = getSelectedWorkshop();
    setWorkshopToInput(workshop);
}

function setWorkshopToInput(workshop) {
    $('#developerId').val(workshop.id);
    $('#name').val(workshop.name);
}

function getSelectedWorkshop() {
    return {
        'id': $selectedRow.children(':nth-child(1)').html(),
        'name': $selectedRow.children(':nth-child(2)').html()
    };
}

function deleteWorkshop() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'workshopDelete',
        data: getSelectedWorkshopId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: removeSelectedRow
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function getSelectedWorkshopId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearEditField() {
    setWorkshopToInput({
        'id': 0,
        'name': ''
    });
    $('#name').focus();
}

function getWorkshopFromInput() {
    return {
        'id': +$('#workshopId').val(),
        'name': $('#name').val()
    };
}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}