package psu.edu.restaurant;

import org.springframework.web.bind.annotation.*;

import java.util.Vector;

import static psu.edu.restaurant.CouponController.coupons;
import static psu.edu.restaurant.CustomerController.custDTOH;
import static psu.edu.restaurant.CustomerController.custH;
import static psu.edu.restaurant.RestaurantController.menuH;

@RestController
public class CartController
{

    @PutMapping("/cart/add")
    public Vector<MenuItem> addToCart(@RequestParam(name = "item") int item, @RequestParam(name = "id") int id)
    {
        if(custH.containsKey(id) && menuH.containsKey(item))
        {
            custH.get(id).setCart(menuH.get(item));
            custDTOH.get(id).setCart(custH.get(id).getCart());

            return custH.get(id).getCart();
        }
        else
        {
            System.out.println("Add Error");
            return null;
        }
    }

    @PutMapping("/cart/clear")
    public Vector<MenuItem> clearCart(@RequestParam(name = "id") int id)
    {
        if(custH.containsKey(id) && !custH.get(id).cart.isEmpty())
        {
            custH.get(id).cart.clear();
            return custH.get(id).cart;
        }
        else if(custH.containsKey(id) && custH.get(id).cart.isEmpty())
        {
            System.out.println("Cart is already empty.");
            return custH.get(id).cart;
        }
        else
        {
            System.out.println("Clear Error");
            return null;
        }
    }

    @DeleteMapping("/cart/delete")
    public Vector<MenuItem> removeFromCart(@RequestParam(name = "item") int item, @RequestParam(name = "id") int id)
    {
        if(custH.containsKey(id) && menuH.containsKey(item)) {
            custH.get(id).getCart().remove(menuH.get(item));
            custDTOH.get(id).getCart().remove(menuH.get(item));

            return custH.get(id).getCart();
        }
        else
        {
            System.out.println("Delete Error");
            return null;
        }
    }

    @PutMapping("/cart/checkout")
    public double checkOut(@RequestParam(name = "id") int id, @RequestParam(name = "method") String method, @RequestParam(name = "cId") int cId)
    {
        if(custH.containsKey(id) && !custH.get(id).cart.isEmpty() &&
                (method.equalsIgnoreCase("Pickup") || method.equalsIgnoreCase("Delivery")))
        {
            double total = 0;

            if(method.equalsIgnoreCase("Delivery") && !custH.get(id).ccn.isEmpty())
            {
                total += 2;
            }
            else if(method.equalsIgnoreCase("Delivery")) {
                System.out.println("Ccn Error");
                return -1;
            }

            for(int i = 0; i < custH.get(id).cart.size(); i++)
            {
                total += custH.get(id).cart.elementAt(i).price;
            }
            if(coupons.get(cId) != null) {
                total = total - (total* coupons.get(cId).getDiscount());
            }


            custH.get(id).cart.clear();
            return total;
        }
        else
        {
            System.out.println("Checkout Error");
            return -1;
        }
    }
}
