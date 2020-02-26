package psu.edu.restaurant;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

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
            return null;
        }
    }

    @DeleteMapping("/cart/delete")
    public Vector<MenuItem> removeFromCart(@RequestParam(name = "item") int item, @RequestParam(name = "id") int id)
    {
        if(custH.containsKey(id) && menuH.containsKey(item)) {
            custH.get(id).getCart().remove(item);
            custDTOH.get(id).getCart().remove(item);

            return custH.get(id).getCart();
        }
        else
        {
            return null;
        }
    }
}
