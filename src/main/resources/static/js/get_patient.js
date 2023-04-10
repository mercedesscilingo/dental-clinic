async function findBy(id) {
  const patient = await fetch(`http://localhost:8080/patients/${id}`, { method: 'GET' }).then(r => r.json());

  document.querySelector("#update_patient_form #paciente_id").value = patient.id;
  document.querySelector("#update_patient_form #nombre").value = patient.name;
  document.querySelector("#update_patient_form #apellido").value = patient.lastname;
  document.querySelector("#update_patient_form #documento").value = patient.document;
  document.querySelector("#update_patient_form #fecha_de_admision").value = patient.admissionDate;
  document.querySelector("#update_patient_form #calle").value = patient.address.street;
  document.querySelector("#update_patient_form #numero").value = patient.address.number;
  document.querySelector("#update_patient_form #ciudad").value = patient.address.town;
  document.querySelector("#update_patient_form #provincia").value = patient.address.province;

  document.querySelector("#div_patient_updating").style.display = "block";
}

function getPatients(){

     const url = 'http://localhost:8080/patients';
     const settings = {
       method: 'GET'
     }

     fetch(url,settings)
     .then(response => response.json())
     .then(data => {
        document.querySelector("#patientTableBody").innerHTML = "";

        for(patient of data){

           var table = document.getElementById("patientTableBody");
           var patientRow =table.insertRow();
           let tr_id = 'tr_' + patient.id;
           patientRow.id = tr_id;

           let deleteButton = '<button' +
                                     ' id=' + '\"' + 'btn_delete_' + patient.id + '\"' +
                                     ' type="button" onclick="deleteBy('+patient.id+')"' +
                                     'class="btn btn-danger btn_delete">' +
                                     '&times' +
                                     '</button>';

           let updateButton = '<button' +
                                     ' id=' + '\"' + 'btn_id_' + patient.id + '\"' +
                                     ' type="button" onclick="findBy('+patient.id+')"' +
                                     ' class="btn btn-info btn_id">' + patient.id +
                                     '</button>';

           patientRow.innerHTML = '<td>' + updateButton + '</td>' +
                 '<td class=\"td_nombre\">' + patient.name.toUpperCase() + '</td>' +
                 '<td class=\"td_apellido\">' + patient.lastname.toUpperCase() + '</td>' +
                 '<td class=\"td_documento\">' + patient.document.toUpperCase() + '</td>' +
                 '<td class=\"td_documento\">' + patient.admissionDate + '</td>' +
                 '<td class=\"td_calle\">' + patient.address.street.toUpperCase() + '</td>' +
                 '<td class=\"td_numero\">' + patient.address.number.toUpperCase() + '</td>' +
                 '<td class=\"td_ciudad\">' + patient.address.town.toUpperCase() + '</td>' +
                 '<td class=\"td_provincia\">' + patient.address.province.toUpperCase() + '</td>' +

                   '<td>' + deleteButton + '</td>';

        };
     })
}

window.addEventListener('load', function () {
    getPatients();

   (function(){
     let pathname = window.location.pathname;
     if (pathname == "./patientList.html") {
         document.querySelector(".nav .nav-item a:last").addClass("active");
     }
   })
 })