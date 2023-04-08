function deleteBy(id){

    const url = '/dentists/'+ id;

    const settings = {
        method: 'DELETE'
        }

    fetch(url,settings)
        .then(response => response.json())

    let row_id = "#tr_" + id;

    document.querySelector(row_id).remove();
    document.querySelector("#div_odontologo_updating").style.display = "none";

}