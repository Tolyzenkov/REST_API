const newUserBtn = document.querySelector('#addUser')
const addUserForm = document.querySelector('#addUserForm')
const deleteUserForm = document.querySelector('#deleteUserForm')
const editUserForm = document.querySelector('#editUserForm')
const allUsersURL = '/rest'
const oneUser = 'rest/admin/'
const deleteUser = 'rest/admin/delete/'
const rolesInDatabase = []
rolesInDatabase[0] = {
    id: 1,
    name: 'ROLE_ADMIN'
}
rolesInDatabase[1] = {
    id: 2,
    name: 'ROLE_USER'
}


const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

const renderUsers = (users) => {
    let temp = ''
    users.forEach((u) => {
        console.log(u)
        temp += "<tr>";
        temp += "<td>" + u.id + "</td>"
        temp += "<td>" + u.name + "</td>"
        temp += "<td>" + u.surname + "</td>"
        temp += "<td>" + u.email + "</td>"
        temp += "<td>" + u.password + "</td>"
        temp += "<td>"
        for (let i = 0; i < u.roles.length; i++) {
            temp += "<div>" + u.roles[i].name + "</div>"
        }
        temp += "</td>"
        temp += "<td>"
        temp += document.createElement('button').innerHTML = "<a class=\"btn btn-primary\" id=\"editButton\" data-userid=\"u.id\">Edit</a>"
        temp += "</td>"
        temp += "<td>"
        temp += document.createElement('button').innerHTML = "<a class=\"btn btn-danger\" id=\"dButton\" data-userid=\"u.id\">Delete</a>"
        temp += "</td></tr>"
    })

    document.getElementById('users').innerHTML = temp;
}


fetch(allUsersURL)
    .then((response) => {
        return response.json();
    })
    .then(data => renderUsers(data))


on(document, 'click', '#dButton', e => {
    const id = e.target.parentNode.parentNode.firstElementChild.innerHTML
    fetch(oneUser + id)
        .then(res => res.json())
        .then((data) => {
            $('#idDel').val(data.id)
            $('#nameDel').val(data.name)
            $('#surnameDel').val(data.surname)
            $('#emailDel').val(data.email)
        })

    $('#deleteModal').modal()


    deleteUserForm.addEventListener('click', evt => {
        evt.preventDefault()
        fetch(deleteUser + id, {
            method: 'DELETE'
        })
            .then(res => res.json())
            .then(data => renderUsers(data))
        $('#deleteModal').modal('hide')
    })
})


newUserBtn.addEventListener('click', (e) => {
    e.preventDefault()

    $("#addModal").modal("show")

})

    addUserForm.addEventListener('submit', ev => {
        ev.preventDefault()
        let roles = $('#roleAdd').val()

        fetch('/rest/admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({

                name: document.querySelector('#nameAdd').value,
                surname: document.querySelector('#surnameAdd').value,
                email: document.querySelector('#emailAdd').value,
                password: document.querySelector('#passAdd').value,
                roles: getSelectedRoles(roles)
            })

        })
            .then(res => res.json())
            .then(data => renderUsers(data))
        addUserForm.reset()
        $("#addModal").modal('hide')
    })


newUserBtn.addEventListener('click', (e) => {
    e.preventDefault()

    $("#addModal").modal("show")

})

on(document, 'click', '#editButton', e => {
    const id = e.target.parentNode.parentNode.firstElementChild.innerHTML
    fetch(oneUser + id)
        .then(res => res.json())
        .then((data) => {
            $('#idEdit').val(data.id)
            $('#nameEdit').val(data.name)
            $('#surnameEdit').val(data.surname)
            $('#emailEdit').val(data.email)
        })

    $('#editModal').modal()
})

editUserForm.addEventListener('submit', ev => {
    ev.preventDefault()
    let roles = $('#roleEdit').val()
    fetch('/rest/admin/edit', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.querySelector('#idEdit').value,
            name: document.querySelector('#nameEdit').value,
            surname: document.querySelector('#surnameEdit').value,
            email: document.querySelector('#emailEdit').value,
            password: document.querySelector('#passEdit').value,
            roles: getSelectedRoles(roles)
        })

    })
        .then(res => res.json())
        .then(data => renderUsers(data))
    editUserForm.reset()
    $("#editModal").modal('hide')
})

function getSelectedRoles(roles) {
    let selectedRoles = {}
    if(rolesInDatabase[0].name === roles[0] && rolesInDatabase[1].name === roles[1]) {
        selectedRoles = rolesInDatabase
    } else if (rolesInDatabase[0].name === roles[0]) {
        selectedRoles = rolesInDatabase[0]
    } else selectedRoles = rolesInDatabase[1]

    return selectedRoles
}