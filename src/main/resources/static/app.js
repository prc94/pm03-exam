function updateList() {
    $.ajax({
        url: "/api/userdata",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            const data_table = $("#data-table")
            $("#contents").children().remove()
            response.forEach(function (elem) {
                data_table
                    .append(
                        $("<tr>")
                        .append($("<td>").text(elem.name))
                        .append($("<td>").text(elem.date))
                        .append($("<td>").text(elem.destination))
                    )
            })
        },
        error: function (xhr, status, error) {
            console.log("Update error. " + error);
        }
    });

}

function isEmpty(str) {
    return (!str || str.length === 0 );
}

function submitData() {
    const nameInput = $("#customer_name")
    const dateInput = $("#tour_date")
    const destInput = $("#destination")

    if (isEmpty(nameInput.val()) || isEmpty(dateInput.val()) || isEmpty(destInput.val())) {
        alert('Заполните все поля!')
        return
    }

    const formData = {
        "name": nameInput.val(),
        "date": dateInput.val(),
        "destination": destInput.val()
    };

    $.ajax({
        url: "/api/submit",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function (response) {
            updateList()
            nameInput.val('')
            dateInput.val('')
            destInput.val('')
        },
        error: function (xhr, status, error) {
            alert('Ошибка!')
            console.log(error)
        }
    });

}