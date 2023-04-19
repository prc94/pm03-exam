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
    const data = document.getElementById("data").value;

    $.ajax({
        url: "/api/submit",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({ "data" : "asasas" }),
        success: function(response) {
            $("#result").html("Data submitted successfully");
        },
        error: function(xhr, status, error) {
            $("#result").html("Error submitting data");
            console.log("Submit error. " + error);
        }
    });
}