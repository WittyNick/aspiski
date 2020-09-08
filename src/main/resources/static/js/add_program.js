$doc.ready(function () {
    let today = new Date().toISOString().split('T')[0];
    $('#date').val(today);
});

function goWelcomePage() {
    location.href='/';
}