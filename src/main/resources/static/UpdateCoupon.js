    /**
 * UpdatCoupon.js
 * Allows us to update specific coupons
 *
 * @author das6301
 */
function updateCoupon(){
    var id = $('#ucId').val();
    var name = $('#coupCode').val()
    var value = $('#coupValueNew').val()


    $('#updateCouponButton').click(function () {

        var url = "/couponlist/update?id=" + id;
            $.ajax({
            url: url,
            method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({name:name, value:parseInt(value)/100}),
                success: function(data){
                console.log(data + "Successfully updated");
            },

            error: function () {
                alert("Coupon Update failed");
            }
        })

    })

}