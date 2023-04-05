window.addEventListener('load', function () {

    document.querySelector("#update_odontologo_form").addEventListener('submit', function (event) {
        event.preventDefault();

        const dentist = {
            id: document.querySelector("#update_odontologo_form #odontologo_id").value,
            name: document.querySelector("#update_odontologo_form #nombre").value,
            lastname: document.querySelector("#update_odontologo_form #apellido").value,
            license: document.querySelector("#update_odontologo_form #matricula").value,
        }

        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dentist)
        }

        fetch(`http://localhost:8080/dentists/${dentist.id}`, settings)
            .then(r => r.json())
            .then(updated_dentist => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Odontologo actualizado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                document.querySelector("#div_odontologo_updating").style.display = "none";
                getDentists();
            })
            .catch(err => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
            })
    })
})
