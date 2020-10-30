const SELECTED_ROW_CLASS = 'table-primary'; // blue
const CHANGED_TEXT_CLASS = 'text-primary'; // blue
const HIDE_CLASS = 'd-none'; // Bootstrap ccs class
const INVALID_INPUT_CLASS = 'is-invalid';

function isStringEmpty(string) {
    return string.trim() === '';
}

function stringDateEnToRu(stringDate) { // yyyy-MM-dd -> dd.MM.yyyy
    let elements = stringDate.split('-');
    return `${elements[2]}.${elements[1]}.${elements[0]}`;
}