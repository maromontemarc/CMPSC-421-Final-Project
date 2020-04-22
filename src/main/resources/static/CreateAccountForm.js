function CreateCustomer() {

    var name = $("#N").val();
    var address = $("#A").val();
    var CC = $("#C").val();
    console.log(name);
        $.ajax({
            method: "POST",
            url: "/cust/create",
            contentType: "application/json",
            data: JSON.stringify({ name: name, address: address, ccn: CC}),
            success: function (result) {
                console.log(result);
        }});



}


