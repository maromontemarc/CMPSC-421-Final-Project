package psu.edu.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

@Controller
public class CouponController {
    static public HashMap<Integer, Coupon> coupons = new HashMap<>();
    private int couponId = 0;

    public CouponController()
    {
        Coupon dHalf = new Coupon("50% off total", .5);
        Coupon dTen = new Coupon("10% off total", .10 );
        Coupon dThirty = new Coupon("30% off total", .3);

        coupons.put(couponId++, dHalf);
        coupons.put(couponId++, dTen);
        coupons.put(couponId++, dThirty);

    }

    @GetMapping("/couponlist")
    public Collection<Coupon> getCoupons()
    {
        return coupons.values();
    }

    @GetMapping("/couponbyid")
    public Coupon getCouponById(@RequestParam(name = "id") int id)
    {
        if(coupons.containsKey(id))
        {
            return coupons.get(id);
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/couponlist/add")
    public String addCoupon(@RequestBody Coupon item)
    {
        if(coupons.containsValue(item)) {
            return "Coupon already exists";

        }
        else {
            coupons.put(couponId++,item);
            return item.getName();
        }



    }


    @DeleteMapping("/couponlist/delete")
    public Collection<Coupon> deleteCoupon(@RequestParam(name = "id") int id)
    {
        if( coupons.containsKey(id))
        {
            coupons.remove(id);
            return coupons.values();
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/couponlist/update")
    public String updateCoupon(@RequestParam(name = "id") int id, @RequestBody Coupon name) {
        if (coupons.containsKey(id)) {
            coupons.remove(id);
            coupons.put(id, name);
            return name.getName();
        } else {
            return "this coupon does not exist.";
        }
    }

}
