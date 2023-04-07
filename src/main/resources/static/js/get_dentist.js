async function findBy(id) {
  const dentist = await fetch(`http://localhost:8080/dentists/${id}`, { method: 'GET' }).then(r => r.json());

  document.querySelector("#update_odontologo_form #odontologo_id").value = dentist.id;
  document.querySelector("#update_odontologo_form #nombre").value = dentist.name;
  document.querySelector("#update_odontologo_form #apellido").value = dentist.lastname;
  document.querySelector("#update_odontologo_form #matricula").value = dentist.license;

  document.querySelector("#div_odontologo_updating").style.display = "block";
}


function getDentists() {

  const url = 'http://localhost:8080/dentists';
  const settings = {
    method: 'GET'
  }

  fetch(url,settings)
  .then(response => response.json())
  .then(data => {
    document.querySelector("#odontologoTableBody").innerHTML = "";

      for(odontologo of data){

        var table = document.getElementById("odontologoTableBody");
        var odontologoRow = table.insertRow();
        let tr_id = 'tr_' + odontologo.id;
        odontologoRow.id = tr_id;

        let deleteButton = '<button' +
                                  ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                  ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                  '&times' +
                                  '</button>';

        let updateButton = '<button' +
                                  ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                  ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                  odontologo.id +
                                  '</button>';

        odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                '<td class=\"td_nombre\">' + odontologo.name.toUpperCase() + '</td>' +
                '<td class=\"td_apellido\">' + odontologo.lastname.toUpperCase() + '</td>' +
                '<td>' + deleteButton + '</td>';

    };
  })
}

window.addEventListener('load', function () {
  getDentists();

  (function(){
    let pathname = window.location.pathname;
    if (pathname == "/dentistList.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })
})