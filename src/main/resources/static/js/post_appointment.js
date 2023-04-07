function formatDate(date) {
    // get the day, month, and year from the date object
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // add 1 to month since it is zero-indexed
    const year = date.getFullYear().toString();

    return `${day}/${month}/${year}`
}

window.addEventListener('load', async function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo odontologo
    const formulario = document.querySelector('#add_new_appointment');

    const [patients, dentists] = await Promise.all([
        fetch('http://localhost:8080/patients', { method: "GET" }).then(response => response.json()),
        fetch('http://localhost:8080/dentists', { method: "GET" }).then(response => response.json()),
    ]);

    const patientSelect = document.querySelector("#patient");
    patientSelect.innerHTML = "";

    for (const patient of patients) {
        const option = document.createElement("option");
        option.text = `${patient.name} ${patient.lastname}`;
        option.value = patient.id;
        patientSelect.add(option);
    }


    const dentistSelect = document.querySelector("#dentist");
    dentistSelect.innerHTML = "";

    for (const dentist of dentists) {
        const option = document.createElement("option");
        option.text = `${dentist.name} ${dentist.lastname}`;
        option.value = dentist.id;
        dentistSelect.add(option);
    }

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const date = new Date(document.querySelector('#fecha').value);

       //creamos un JSON que tendrá los datos de un nuevo odontologo
        const formData = {
            date: formatDate(date),
            patientId: document.querySelector('#patient').value,
            dentistId: document.querySelector('#dentist').value,
        };

        //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = 'http://localhost:8080/appointments';
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
                 //Si no hay ningun error se muestra un mensaje diciendo que el odontologo
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que el odontologo
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otro odontologo
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#fecha').value = "";
        document.querySelector('#patient').value = "";
        document.querySelector('#dentist').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/dentistList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});