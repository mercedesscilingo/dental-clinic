function formatDate(date) {
    // get the day, month, and year from the date object
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // add 1 to month since it is zero-indexed
    const year = date.getFullYear().toString();

    return `${day}/${month}/${year}`
}

async function findBy(id) {
  const dentist = await fetch(`http://localhost:8080/appointments/${id}`, { method: 'GET' }).then(r => r.json());

  document.querySelector("#update_appointment_form #appointment_id").value = appointment.id;
  document.querySelector("#update_appointment_form #fecha").value = appointment.date;
  document.querySelector("#update_appointment_form #patient").value = appointment.patient;
  document.querySelector("#update_appointment_form #dentist").value = appointment.dentist;

  document.querySelector("#div_appointment_updating").style.display = "block";
}


function getAppointments() {

  const url = 'http://localhost:8080/appointments';
  const settings = {
    method: 'GET'
  }

  fetch(url,settings)
  .then(response => response.json())
  .then(data => {
    document.querySelector("#appointmentTableBody").innerHTML = "";

      for(appointment of data){

        var table = document.getElementById("appointmentTableBody");
        var appointmentRow = table.insertRow();
        let tr_id = 'tr_' + appointment.id;
        appointmentRow.id = tr_id;

        let deleteButton = '<button' +
                                  ' id=' + '\"' + 'btn_delete_' + appointment.id + '\"' +
                                  ' type="button" onclick="deleteBy('+appointment.id+')" class="btn btn-danger btn_delete">' +
                                  '&times' +
                                  '</button>';

        let updateButton = '<button' +
                                  ' id=' + '\"' + 'btn_id_' + appointment.id + '\"' +
                                  ' type="button" onclick="findBy('+appointment.id+')" class="btn btn-info btn_id">' +
                                  appointment.id +
                                  '</button>';

        const date = new Date(appointment.date);

        appointmentRow.innerHTML = '<td>' + updateButton + '</td>' +
                '<td class=\"td_fecha\">' + formatDate(date) + '</td>' +
                '<td class=\"td_patient\">' + appointment.patient.name.toUpperCase() +" " + appointment.patient.lastname.toUpperCase() + '</td>' +
                '<td class=\"td_dentist\">' + appointment.dentist.name.toUpperCase() + " " +appointment.dentist.lastname.toUpperCase() + '</td>' +
                '<td>' + deleteButton + '</td>';

    };
  })
}

window.addEventListener('load', function () {
  getAppointments();

  (function(){
    let pathname = window.location.pathname;
    if (pathname == "./appointmentList.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })
})