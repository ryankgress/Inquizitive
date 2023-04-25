function checkHost(isHost) {
    if(isHost == 'false') {
        var ele = document.getElementById('notHost');
        ele.innerHTML = 'Must be a Host account to create a Trivia.';
        ele.style = "color:red;";
        var sidebar = document.getElementById('offcanvasScrolling');
        sidebar.classList.remove('show');
    } else {
        console.log("Is a host " + isHost);
    }
}