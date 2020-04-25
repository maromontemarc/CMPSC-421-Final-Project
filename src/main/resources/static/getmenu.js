function getmenu(){
    var menulist;


    $.get("/menu", function(data){
        var i =0;
        $("#menu").text("");
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