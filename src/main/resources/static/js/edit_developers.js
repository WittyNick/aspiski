// TODO: add input validation before save

const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function saveDeveloper() {
    let developer = getDeveloperFromInput();
    $.ajax({
        type: 'POST',
        url: 'developerSave',
        data: JSON.stringify(developer),
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: addDeveloperToTable
    });
}

function addDeveloperToTable(developer) {
    let $row = getTableRow(developer);
    $('#developersTbody').prepend($row);
    clearEditField();
    if (!isNothingSelected()) {
        removeSelectedRow();
    }
}

function getTableRow(developer) {
    return $(
        '<tr onclick="handleRowClick(this)">' +
        '<td>' + developer.id + '</td>' +
        '<td>' + developer.name + '</td>' +
        '</tr>'
    );
}

function editDeveloper() {
    if (isNothingSelected()) {
        return;
    }
    clearEditField();
    let developer = getSelectedDeveloper();
    setDeveloperToInput(developer);
}

function setDeveloperToInput(developer) {
    $('#developerId').val(developer.id);
    $('#name').val(developer.name);
}

function getSelectedDeveloper() {
    return {
        id: $selectedRow.children(':nth-child(1)').html(),
        name: $selectedRow.children(':nth-child(2)').html()
    };
}

function deleteDeveloper() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'developerDelete',
        data: getSelectedDeveloperId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: removeSelectedRow
    });
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function getSelectedDeveloperId() {
    return $selectedRow.children(':first-child').html();
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearEditField() {
    setDeveloperToInput({
        id: 0,
        name: ''
    });
    $('#name').focus();
}

function getDeveloperFromInput() {
    return {
        id: +$('#developerId').val(),
        name: $('#name').val()
    };
}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}