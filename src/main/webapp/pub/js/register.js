/**
 * Will validate that username is not taken yet
 */
function validateUser() {

}

/**
 * Checks if Password matches Confirm
 * Also confirms Password is of right length and uses required character types
 * PREVENT DEFAULT
 */
function validatePass() {
    let passInput = document.getElementById("passwordInput");
    let passValue = passInput.value;
    let confirmPassInput = document.getElementById("confirmPasswordInput");
    let confirmPassValue = confirmPassInput.value;

    var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/;
    if (!passValue.match(passw)) {
        passInput.classList.add("is-invalid");
        if (!document.body.contains(document.getElementById("newPassHelp"))) {
            const para = document.createElement("p");
            const node = document.createTextNode("Please enter a valid Password");
            para.appendChild(node);
            para.style.cssText = "color:red; margin-bottom:0; text-align:right";
            const element = document.getElementById("passField");
            element.appendChild(para);
            para.id = "newPassHelp";
        }
    } else {
        try {
            document.getElementById("newPassHelp").remove();
            passInput.classList.remove("is-invalid");
        } catch (e) {
            console.log(e);
        }
    }

    // Handles Pass and ConfirmPass matching
    if(passValue != confirmPassValue) {
        confirmPassInput.classList.add("is-invalid");

        if (!document.body.contains(document.getElementById("confirmPassHelp"))) {
            const para2 = document.createElement("p");
            const node2 = document.createTextNode("The two passwords must match");
            para2.appendChild(node2);
            para2.style.cssText = "color:red; margin-bottom:0; text-align:right";
            const element2 = document.getElementById("confirmField");
            element2.appendChild(para2);
            para2.id = "confirmPassHelp";
        }
    } else {
        try {
            document.getElementById("confirmPassHelp").remove();
            confirmPassInput.classList.remove("is-invalid");
        } catch (e) {
            console.log(e);
        }
    }

}