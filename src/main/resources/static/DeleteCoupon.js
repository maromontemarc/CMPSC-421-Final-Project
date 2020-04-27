/**
 * DeleteCoupon.js
 * Allows us to delete new Coupons
 *
 * @author das6301
 */

function deleteCoupon() {
    var id = $('#dcId').val();

        $.ajax({
            method: 'DELETE',
            url: '/couponlist/delete',
            data: {
                id: id
            },

            success: function (data) {
                console.log(data);
            },
            error: function () {
                alert("Coupon deletion failed");
            }

        });



}