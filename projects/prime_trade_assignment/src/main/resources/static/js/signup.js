let buttonDiv = document.getElementById("submitDiv");
buttonDiv.onclick = () => validate();
let passwordEle = document.getElementById("passwordInput");
let confirmPasswordEle = document.getElementById("confirmPasswordInput");
document.getElementById("showPassword").addEventListener("click",() => togglePasswordVisibility("passwordInput"));
document.getElementById("showConfirmPassword").addEventListener("click",() => togglePasswordVisibility("confirmPasswordInput"));

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
        if(password.length==0) return "noPassword";
        if(password.length<8 || !passwordContainsNumber || !passwordContainsLowerCase || !passwordContainsUpperCase || !passwordContainsSpecialSymbol)
            return "incorrectPasswordFormat";
        if(confirmPassword==0) return "noConfirmPassword";
        if(password!=confirmPassword) return "passwordsNotEqual";
    };
    switch(validator()){
        case "noUserName": alert("Username field cannot be empty");
                        break;
        case "noEmail": alert("Email field cannot be empty");
                        break;
        case "noPassword": alert("Password field cannot be empty");
                        break;
        case "noConfirmPassword": alert("Confirm password field cannot be empty");
                        break;
        case "incorrectPasswordFormat": alert("Incorrect password format\nPassword should contain:\n1. a number\n2. an upper case letter\n3. a lower case letter\n4. a special symbol");
                        break;
        case "passwordsNotEqual": alert("Password and confirm password should be same");
                        break;
    }

}