$(document).ready(function() {
    let id;

    $('.table .btn').on('click',function(event) {
        event.preventDefault();
        let href = $(this).attr('href');
        let text = $(this).text();

        if (text == 'Edit') {
            $.get(href, function(user, status) {
                $('#idEdit').val(user.id);
                $('#nameEdit').val(user.name);
                $('#surnameEdit').val(user.surname);
                $('#emailEdit').val(user.email);
            });

            $('#editModal').modal();
        } else if (text == 'Delete') {
            $.get(href, function(user, status) {
                $('#idDel').val(user.id)
                $('#nameDel').val(user.name);
                $('#surnameDel').val(user.surname);
                $('#emailDel').val(user.email);
                id = user.id;
                let dhref = 'admin/' + id;
                $('.modal #delHref').attr('href', dhref);
            });

            $('#deleteModal').modal();
        }
    });

    $('#addUser').on('click',function(event) {
        event.preventDefault();
        $('#addModal').modal();
    });
});