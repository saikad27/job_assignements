//let buttonDiv = document.getElementById("submitDiv");
//buttonDiv.onclick = () => validate();

let passwordEle = document.getElementById("passwordInput");
let confirmPasswordEle = document.getElementById("confirmPasswordInput");
document.getElementById("showPassword").addEventListener("click",() => togglePasswordVisibility("passwordInput"));
document.getElementById("showConfirmPassword").addEventListener("click",() => togglePasswordVisibility("confirmPasswordInput"));
document.getElementById("register_button").addEventListener("click",()=>{
    let result = validate();
    //console.log("result:"+result);
    if(result!=null){
        //fetch()
        fetch('/signup',{
        method:"POST",
        headers:{"content-type":"application/json"},
        body:JSON.stringify(result)
        })
        .then(response => {
            const data = response.json();
            console.log(data);
            if (!response.ok) {
                // Pass your backend message to catch()
                throw new Error("Username/email already exists");
            }
            return data;
        })
        .then(data => {
            //window.location.href="/login";
            document.getElementById("userNameInput").value = "";
            document.getElementById("emailInput").value = "";
            document.getElementById("passwordInput").value = "";
            document.getElementById("confirmPasswordInput").value = "";
            console.log(data);
            document.getElementById("registration_message").textContent = "Registration Successful";
            }
        ).catch(err => {
        window.location.href="/signup";
        alert(err);
        console.error("Error registering user : ",err);
        });
    }
});

function togglePasswordVisibility(id){
    let passwordElement = document.getElementById(id);
    if(passwordElement.type==='password'){
        passwordElement.type = "text";
    }else{
        passwordElement.type = "password";
    }
}
function validate(){
    let userName = document.getElementById("userNameInput").value;
    let email = document.getElementById("emailInput").value;
    let password = document.getElementById("passwordInput").value;
    let confirmPassword = document.getElementById("confirmPasswordInput").value;
    let passwordContainsNumber = /\d/.test(password);
    let passwordContainsUpperCase = /[A-Z]/.test(password);
    let passwordContainsLowerCase = /[a-z]/.test(password);
    let passwordContainsSpecialSymbol = /[!@#$%^&*(\-),.?":{}|<>]/.test(password);
    let validator = () => {
        if(userName.length==0) return "noUserName";
        if(email.length==0) return "noEmail";
        if(!email.includes("@")) return "invalidEmail";
        if(password.length==0) return "noPassword";
        if(password.length<8 || !passwordContainsNumber || !passwordContainsLowerCase || !passwordContainsUpperCase || !passwordContainsSpecialSymbol)
            return "incorrectPasswordFormat";
        if(confirmPassword==0) return "noConfirmPassword";
        if(password!=confirmPassword) return "passwordsNotEqual";
    };
    switch(validator()){
        case "noUserName": alert("Username field cannot be empty");
                            return null;
        case "noEmail": alert("Email field cannot be empty");
                            return null;
        case "invalidEmail": alert("Invalid email");
                            return null;
        case "noPassword": alert("Password field cannot be empty");
                            return null;
        case "noConfirmPassword": alert("Confirm password field cannot be empty");
                            return null;
        case "incorrectPasswordFormat": alert("Incorrect password format\nPassword should contain:\n1. a number\n2. an upper case letter\n3. a lower case letter\n4. a special symbol");
                            return null;
        case "passwordsNotEqual": alert("Password and confirm password should be same");
                            return null;
    }
    return {"userName":userName,"email":email,"password":password};
}

document.getElementById("login_button").addEventListener("click",() => {window.location.href = 'login'});