function openList(){
    var custList;
    $.get("/cust/list", function(data){
        var i =0;
        $("#customers").text("");
        while(data[i].name != "") {

            custList = data[i].name;
            $("#customers").append(" " + custList);

            if(data[i+1].name != ""){
                $("#customers").append(", ");
            }
            i++;
        }

    });

}
function removeCust(){
    var id = $("#cId").val();
    $.ajax({
        method: "Delete",
        url: "/cust/delete",
        data: {
                id:  id
         }
         ,
        success: function (result) {

            console.log(result);
        }});
}
function updateCustomer(){
    var id = $("#uCust").val();
    var name = $("#N").val();
    var Address = $("#A").val();
    var CC = $("#C").val();



        var url = "/cust/update?id=" + id;
        $.ajax({
            url: url,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({name:name, address: Address, ccn: CC }),
            success: function(data){
                console.log(data + "Successfully updated");
            },

            error: function () {
                alert("Coupon Update failed");
            }
        })



}