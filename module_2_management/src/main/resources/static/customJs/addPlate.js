function addPlate() {
    var param = {
        newPlate: $("#new_plate").val(),
        pass: $("#pass").val()

    }
    var ser_data = JSON.stringify(param);

    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=UTF-8',
        url: 'addplate',
        data: ser_data,

        success: function(data) {
            alert(data);
        },
        error: function(data) {
            alert(data);
        }

    });

}