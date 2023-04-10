function deleteBy(id){

    const url = '/patients/'+ id;

    const settings = {
        method: 'DELETE'
        }

    fetch(url,settings)
        .then(response => {
            console.log("Deleting patient");
        })

    let row_id = "#tr_" + id;

    document.querySelector(row_id).remove();
    document.querySelector("#div_patient_updating").style.display = "none";

}