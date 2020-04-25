function getmenu(){
    var menulist;
    $("#menu").text("");

    $.get("/menu", function(data){
        var i =0;
        console.log($("#menu").val());
        while(data[i] != null) {

            menulist = data[i].item;
            $("#menu").append(" " + menulist);

            if(data[i+1] != null){
                $("#menu").append(", ");
            }
            i++;
        }

    });

}