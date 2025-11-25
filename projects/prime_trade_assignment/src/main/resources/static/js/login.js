document.getElementById("viewPassword").addEventListener("click",()=>{
    let ele = document.getElementById("passwordInput");
    if(ele.type=="password"){
        ele.type = "text";
    }else{
        ele.type = "password";
    }
});

document.getElementById("submitButton").addEventListener("click",() => {
    let result = validate();
    if(result!=null){
        fetch('/login',{
                method:"POST",
                headers:{"content-type":"application/json"},
                body:JSON.stringify(result)
                })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                }).catch(err => console.error("Error logging in user : ",err));
    }

});

function validate(){
        let userName = document.getElementById("userIdInput").value;
        let password = document.getElementById("passwordInput").value;
        if(userName.length==0){
            alert("User name field should not be empty");
            return null;
        }if(password.length==0){
            alert("Password field should not be empty");
            return null;
        }
        let passwordContainsNumber = /\d/.test(password);
        let passwordContainsUpperCase = /[A-Z]/.test(password);
        let passwordContainsLowerCase = /[a-z]/.test(password);
        let passwordContainsSpecialSymbol = /[!@#$%^&*(\-),.?":{}|<>]/.test(password);
        if(password.length<8 || !passwordContainsNumber || !passwordContainsLowerCase || !passwordContainsUpperCase || !passwordContainsSpecialSymbol){
            alert("Incorrect password format\nPassword should contain:\n1. a number\n2. an upper case letter\n3. a lower case letter\n4. a special symbol");
            return null;
        }
        return {"userName":userName,"password":password};
}
