/**
 * CreateCoupon.js
 * Allows us to create new Coupons
 *
 * @author das6301
 */

function createCoupon() {
    var name = $('#coupName').val();
    var value = $('#coupValue').val();



    $('#couponButton').click(function () {
        $.ajax({
            method: 'POST',
            url: 'https://localhost:8080/couponlist/add',
            contentType: 'application/json',
            data: JSON.stringify({name: name, value: parseInt(value) / 100}),

            success: function (data) {
                console.log(data);
            },
            error: function () {
                alert("Coupon failed");
            }

        });


    });

}