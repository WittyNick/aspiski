const SEARCH_DELAY_TIME = 500; // ms

let $selectedRow = null,
    $programsTbody,
    $filterCheckbox,
    $filter,
    $inputPart,
    $inputMachine,
    $inputSystem,
    $inputProgram,
    $inputWorkshop;

$(function () {
    $programsTbody = $('#programsTable tbody');
    $filterCheckbox = $('#orderCheckbox');
    $filter = $('#filter');
    $inputPart = $('#part');
    $inputMachine = $('#machine');
    $inputSystem = $('#controlSystem');
    $inputProgram = $('#programNumber');
    $inputWorkshop = $('#workshop');
    addActionHandlers();
});

function addActionHandlers() {
    $inputPart.on('input', delay(filterTable, SEARCH_DELAY_TIME));
    $inputProgram.on('input', delay(filterTable, SEARCH_DELAY_TIME));
    $filterCheckbox.on('input', delay(filterTable, SEARCH_DELAY_TIME));
    $inputMachine.on('input', delay(filterTable));
    $inputSystem.on('input', delay(filterTable));
    $inputWorkshop.on('input', delay(filterTable));
    $('#clear').on('click', delay(clearHandler));
    $filterCheckbox.on('input', checkboxHandler);
    $('#info').on('click', showInfo);
    $('#edit').on('click', editCNCProgram);
    $('#delete').on('click', deleteButtonHandler);
    $programsTbody.children('tr').on('click', selectRow);
    $(document).on('keydown', keyPressHandler);
}

function clearHandler() {
    $filter.trigger('reset');
    $inputPart.focus();
    $programsTbody.children('tr').removeClass(HIDE_CLASS);
}

function keyPressHandler(event) {
    if (isNothingSelected()) {
        return;
    }
    switch (event.keyCode) {
        case 40: // down
            selectNextRow();
            break;
        case 38: // up
            selectPrevRow();
            break;
    }
}

function selectNextRow() {
    let $row = $selectedRow.next();
    while ($row !== null) {
        if ($row.hasClass(HIDE_CLASS)) {
            $row = $row.next();
        } else {
            $row.click();
            break;
        }
    }
}

function selectPrevRow() {
    let $row = $selectedRow.prev();
    while ($row !== null) {
        if ($row.hasClass(HIDE_CLASS)) {
            $row = $row.prev();
        } else {
            $row.click();
            break;
        }
    }
}

function showInfo() {
    if (isNothingSelected()) {
        return;
    }
    let info = $selectedRow.children(':eq(12)').html();
    if (isStringEmpty(info)) {
        info = '&#x2718; примечания нет';
    }
    $('#modalBodyInfo').html(info);
    $('#infoModal').modal();
}

function deleteButtonHandler() {
    if (isNothingSelected()) {
        return;
    }
    let part = $selectedRow.children(':eq(1)').html();
    if (confirm(`Удалить "${part}"?`)) {
        let id = getSelectedProgramId();
        deleteProgram(id);
    }
}

function editCNCProgram() {
    if (isNothingSelected()) {
        return;
    }
    let id = getSelectedProgramId();
    $('#programId').val(id);
    $('#editProgramById').submit();
}

function deleteProgram(stringId) {
    $.ajax({
        type: 'POST',
        url: 'programDelete',
        data: stringId, // String
        contentType: 'text/plain; charset=UTF-8',
        success: function (wasDeleted) {
            if (wasDeleted) {
                removeSelectedRow();
            } else {
                alert('Не удалось удалить!\nКто-то её уже успел удалить до тебя.');
            }
        }
    });
}

function getSelectedProgramId() {
    return $selectedRow.children(':first-child').html();
}

function removeSelectedRow() {
    $selectedRow.remove();
    $selectedRow = null;
}

function selectRow() {
    if (!isNothingSelected()) {
        $selectedRow.removeClass(SELECTED_ROW_CLASS);
    }
    $selectedRow = $(this);
    $selectedRow.addClass(SELECTED_ROW_CLASS);
}

function unselectRow() {
    if (!isNothingSelected()) {
        $selectedRow.removeClass(SELECTED_ROW_CLASS);
        $selectedRow = null;
    }
}

function isNothingSelected() {
    return $selectedRow === null;
}

// ----- Search filter -----

function delay(callback, ms) {
    let timerId = 0;
    return function () {
        clearTimeout(timerId);
        timerId = setTimeout(callback, ms || 0);
    };
}

function filterTable() {
    unselectRow();
    $programsTbody.children('tr').each(function (i, row) {
        let $row = $(row);
        isMatches($row) ? $row.removeClass(HIDE_CLASS) : $row.addClass(HIDE_CLASS);
    });
}

function isMatches($tableRow) {
    let filter = parseFilterOptions();
    let row = parseTableRowOptions($tableRow);

    let partCompareIndex = row.part.indexOf(filter.part);
    return (partCompareIndex === 0 || partCompareIndex !== -1 && !filter.isExactSearch) &&
        (filter.machineId === 0 || row.machineId === filter.machineId) &&
        (filter.systemId === 0 || row.systemId === filter.systemId) &&
        row.program.indexOf(filter.program) !== -1 &&
        (filter.workshopId === 0 || row.workshopId === filter.workshopId);
}

function parseFilterOptions() {
    return {
        isExactSearch: $filterCheckbox.is(':checked'),
        part: $inputPart.val().toLowerCase(),
        machineId: +$inputMachine.val(),
        systemId: +$inputSystem.val(),
        program: $inputProgram.val().toLowerCase(),
        workshopId: +$inputWorkshop.val()
    };
}

function parseTableRowOptions($tableRow) {
    let $columns = $tableRow.children();
    return {
        part: $columns.eq(1).html().toLowerCase(),
        machineId: +$columns.eq(2).html(),
        systemId: +$columns.eq(4).html(),
        program: $columns.eq(7).html().toLowerCase(),
        workshopId: +$columns.eq(8).html()
    };
}

function checkboxHandler() {
    $inputPart.focus();
}