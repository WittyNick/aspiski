function isStringEmpty(string) {
    return string.trim() === '';
}

function isUndefined(value) {
    return typeof value === 'undefined';
}

/*
Convert String from "yyyy-MM-dd" to "dd.MM.yyyy" format.
 */
function toStringDateRu(stringDateEn) {
    let elements = stringDateEn.split('-');
    return elements[2] + '.' + elements[1] + '.' + elements[0];
}