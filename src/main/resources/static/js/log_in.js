const AJAX_CHECK_USER_URL = 'checkUser';

let $password,
    $enterBtn;

$(function () {
    $password = $('#password');
    $enterBtn = $('#enterBtn');
    $enterBtn.on('click', logIn);
    $('#cancelBtn').on('click', goWelcomePage);
    $(document).on('keydown', keyPressHandler);
});

function keyPressHandler(event) {
    if (event.keyCode === 13) {
        $enterBtn.click();
    }
}

function logIn() {
    let password = $password.val();
    if (!password) {
        $password.focus();
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
                $password.addClass('is-invalid');
                $password.select();
            }
        }
    });
}