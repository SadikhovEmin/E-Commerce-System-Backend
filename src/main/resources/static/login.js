function login() {
  let email = document.getElementById("Email").value
  let password = document.getElementById("pass").value
  let code  = document.getElementById("code").value
  
  console.log(email)
  console.log(password)
  console.log(code)
  if(document.getElementById("customerOption").checked){
    axios.post('http://localhost:8080/customer/login', {
      password : password,
      email : email,
      code : code
    })
    .then(function (response) {
      console.log(response);
      if(response.data == "loginPage.html")
        alert("Wrong password or 2FA code !")
      else
        alert("Logged in successfully !")
      window.location.href = response.data
    })
    .catch(function (error) {
      console.log(error);
    });
  }
  else{
    axios.post('http://localhost:8080/storeOwner/login', {
      password : password,
      email : email,
      code : code
    })
    .then(function (response) {
      console.log(response);
      if(response.data == "loginPage.html")
        alert("Wrong password or 2FA code !")
      else
        alert("Logged in successfully !")
      window.location.href = response.data
    })
    .catch(function (error) {
      console.log(error);
    });
  }
}