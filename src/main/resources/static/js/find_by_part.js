const SELECTED_ROW_CLASS = 'table-primary';

let $selectedRow = $('<tr>');

function goWelcomePage() {
    $(location).prop('href', '/');
}

function goAddProgram() {
    $(location).prop('href', '/addProgram');
}

function editCNCProgram() {
    if (isNothingSelected()) {
        return;
    }
    let id = $selectedRow.children(':first-child').html();
    $('#programId').val(id);
    $('#editProgramById').submit();
}

function deleteCNCProgram() {
    if (isNothingSelected()) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'programDelete',
        data: getSelectedProgramId(), // String
        contentType: 'text/plain; charset=UTF-8',
        success: removeSelectedRow
    });
}

function getSelectedProgramId() {
    return $selectedRow.children(':first-child').html();
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = $('<tr>');
}

function handleRowClick(row) {
    $selectedRow.removeClass(SELECTED_ROW_CLASS);
    $selectedRow = $(row);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}

function unselectRow() {
    if (!isNothingSelected()) {
        $selectedRow.removeClass(SELECTED_ROW_CLASS);
        $selectedRow = $('<tr>');
    }
}

function isNothingSelected() {
    return  $selectedRow.is(':empty');
}

function clearEditField() {
    let $filter = $('#filter');
    $filter.val('');
    $('#programsTable tbody tr').removeClass('hidden');
    $filter.focus();
}

// ----- Search filter -----

function filterTable() {
    unselectRow();
    let isCheckboxChecked = $('#orderCheckbox').is(':checked');
    let filterValue = $('#filter').val().toLowerCase();
    $('#programsTable tbody tr').each(function(i, row) {
        let $row = $(row);
        let value = $row.children(':eq(1)').html().toLowerCase();
        if (isCheckboxChecked) {
            value.indexOf(filterValue) === -1 ? $row.addClass('hidden') : $row.removeClass('hidden');
        } else {
            value.indexOf(filterValue) !== 0 ? $row.addClass('hidden') : $row.removeClass('hidden');
        }
    });
}

function handleCheckbox() {
    filterTable();
    $('#filter').focus();
}

// ----- Form validation -----
