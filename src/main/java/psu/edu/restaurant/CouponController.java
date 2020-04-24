package psu.edu.restaurant;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

@RestController
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
    public HashMap<Integer, Coupon> getCoupons()
    {
        return coupons;
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

    @RequestMapping(value= "/couponlist/add", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
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

    //@PutMapping("/couponlist/update")
    @RequestMapping(value= "/couponlist/update", method = RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON_VALUE)
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
