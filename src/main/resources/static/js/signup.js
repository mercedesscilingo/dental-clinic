window.addEventListener('load', function(){
  const form = document.querySelector('#register_form')

  form.addEventListener('submit', function(event){
    event.preventDefault();

    const formData = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    }

    const url = 'http://localhost:8080/auth/register';

    const settings = {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(formData)
    }

    fetch(url, settings)
      .then(response => {
        if (response.ok) {
        const jwtToken = response.headers.get('Authorization');
            if(jwtToken){
                localStorage.setItem('jwt', jwtToken);
                location.replace("./home.html")
                resetUploadForm();
            }
            else{
            document.getElementById("result").innerHTML = "Incorrect username or password.";
            }
        }else{
        document.getElementById("result").innerHTML = "Incorrect username or password.";
        }
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
       document.querySelector('#username').value = "";
       document.querySelector('#password').value = "";
     }
  })