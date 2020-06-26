// This Function Button Value(Plate ID) send to backend for delete
// Backend tarafı sıkıntılı
// TODO : !
function deletePlate(id) {
    var param = {
        id: id
    }

    var ser_data = JSON.stringify(param);

    $.ajax({
        type: "POST",
        url: 'deleteplate',
        data: ser_data,
    });

}