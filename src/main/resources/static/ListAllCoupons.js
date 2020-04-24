/**
 * ListAllCoupons.js
 * Allows us to list all Coupons
 *
 * @author das6301
 */

function listAllCoupons(){
    var custList;

    $("#showAllCouponLabel").text("");

    $.get("/couponlist", function(data){
        var i =0;
        while(data[i] != null) {

            custList = data[i].name;
            $("#showAllCouponLabel").append(" " + custList);

            if(data[i+1] != null){
                $("#showAllCouponLabel").append(", ");
            }
            i++;
        }

    });

}