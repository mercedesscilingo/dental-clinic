window.addEventListener('load', function () {

    document.querySelector("#update_patient_form").addEventListener('submit', function (event) {
        event.preventDefault();

        const patient = {
             id: document.querySelector('#update_patient_form #paciente_id').value,
             name: document.querySelector('#update_patient_form #nombre').value,
             lastname: document.querySelector('#update_patient_form #apellido').value,
             document: document.querySelector('#update_patient_form #documento').value,
             admissionDate: '2023-04-06',
             address:{
                 street: document.querySelector('#update_patient_form #calle').value,
                 number: document.querySelector('#update_patient_form #numero').value,
                 town: document.querySelector('#update_patient_form #ciudad').value,
                 province: document.querySelector('#update_patient_form #provincia').value
             }
        }


        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(patient)
        }

        fetch(`http://localhost:8080/patients/${patient.id}`, settings)
            .then(r => r.json())
            .then(updated_patient => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Paciente actualizado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                document.querySelector("#div_patient_updating").style.display = "none";
                getPatients();
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