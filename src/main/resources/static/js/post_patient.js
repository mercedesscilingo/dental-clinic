window.addEventListener('load', function () {


    const formulario = document.querySelector('#add_new_patient');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
            name: document.querySelector('#nombre').value,
            lastname: document.querySelector('#apellido').value,
            document: document.querySelector('#documento').value,
            address:{
              street: document.querySelector('#calle').value,
              number: document.querySelector('#numero').value,
              town: document.querySelector('#ciudad').value,
              province: document.querySelector('#provincia').value,
            }
        };

        const url = 'http://localhost:8080/patients';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Paciente agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";

                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#documento').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#ciudad').value = "";
        document.querySelector('#provincia').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "./patientList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});