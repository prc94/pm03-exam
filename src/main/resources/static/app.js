function updateList() {
    $.ajax({
        url: "/api/userdata",
        type: "GET",
        contentType: "application/json",
        success: function(response) {
            const data_list = $("#data-list")
            data_list.children().remove()
            response.forEach(function (elem) {
                data_list
                    .append($("<li>")
                        .append($("<div>")
                            .append($("<p>").text('ФИО: ' + elem.name))
                            .append($("<p>").text('Дата: ' + elem.date))
                            .append($("<p>").text('Направление: ' + elem.destination))
                            .append($("<br>"))
                        )
                )
            })
        },
        error: function(xhr, status, error) {
            console.log("Submit error. " + error);
        }
    });

}

function submitData() {
/*    const name = document.getElementById("customer_name")
    const date = document.getElementById("tour_date").value;*/
    event.preventDefault();

    const formData = {
        "name": $("#customer_name").val(),
        "date": $("#tour_date").val(),
        "destination": $("#destination").val()
    };

    $.ajax({
        url: "/api/submit",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function(response) {
            updateList()
        },
        error: function(xhr, status, error) {
            $("#result").html("Error submitting data");
            console.log(error)
        }
    });
}