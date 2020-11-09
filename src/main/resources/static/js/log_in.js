const AJAX_CHECK_USER_URL = 'checkUser';

let $password;

$(function () {
    $password = $('#password');
    $('#enterBtn').on('click', logIn);
    $('#cancelBtn').on('click', goWelcomePage);
});

function logIn() {
    let password = $password.val();
    if (!password) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: AJAX_CHECK_USER_URL,
        data: password,
        contentType: 'text/plain; charset=UTF-8',
        dataType: 'json',
        success: function (isValid) {
            if (isValid === true) {
                location.href = '/';
            } else {
                $password.val('');
                $password.focus();
                $password.addClass('is-invalid');
            }
        }
    });
}