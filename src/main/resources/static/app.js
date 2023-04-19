function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/login", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                document.getElementById("result").innerHTML = "Login successful";
                window.location.href = "/submit";
            } else if (xhr.status === 401) {
                document.getElementById("result").innerHTML = "Invalid username or password";
            } else {
                document.getElementById("result").innerHTML = "Error logging in";
            }
        }
    };
    xhr.send(JSON.stringify({ "username": username, "password": password }));
}

function submitData() {
    var username = document.getElementById("username").value;
    var data = document.getElementById("data").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/submit?username=" + username, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("result").innerHTML = "Data submitted successfully";
        } else {
            document.getElementById("result").innerHTML = "Error submitting data";
        }
    };
    xhr.send(JSON.stringify({ "data": data }));
}