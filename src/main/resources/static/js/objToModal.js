$(document).ready(function() {

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        const href = $(this).attr('href');

        $.get(href, function (user, status) {
            console.log('user.id');
            $('.myForm #name').val(user.id);
            $('.myForm #surname').val(user.surname);
            $('.myForm #email').val(user.email);

        });

        $('.myForm #exampleModal').showModal();
    });
});