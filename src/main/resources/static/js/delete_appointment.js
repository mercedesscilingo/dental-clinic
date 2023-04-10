function deleteBy(id){


    const url = '/appointments/'+ id;

    const settings = {
        method: 'DELETE'
        }

    fetch(url,settings)
        .then(response => {
            console.log("Deleting appointment");
        })

    let row_id = "#tr_" + id;

    document.querySelector(row_id).remove();
    document.querySelector("#div_appointment_updating").style.display = "none";

}