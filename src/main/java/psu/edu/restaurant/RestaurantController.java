package psu.edu.restaurant;
import static psu.edu.restaurant.CouponController.coupons;
import static psu.edu.restaurant.CustomerController.custH;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Controller
public class RestaurantController
{
    static public HashMap<Integer, MenuItem> menuH = new HashMap<>();
    private int menuID = 0;

    public RestaurantController()
    {
        MenuItem steak = new MenuItem("Steak", 12);
        MenuItem burger = new MenuItem("Burger", 8);
        MenuItem cheeseBurger = new MenuItem("Cheeseburger", 9);
        MenuItem pizza = new MenuItem("Pizza", 10);
        MenuItem grilledChicken = new MenuItem("Grilled Chicken", 9);
        menuH.put(menuID++, steak);
        menuH.put(menuID++, burger);
        menuH.put(menuID++, cheeseBurger);
        menuH.put(menuID++, pizza);
        menuH.put(menuID++, grilledChicken);



    }

    @GetMapping("/menu")
@ResponseBody
    public HashMap<Integer,MenuItem> getMenu()
    {
        return menuH;
    }

    @RequestMapping(value = "/menupage", method = RequestMethod.GET)
    public String menupage(Model m) {
        m.addAttribute("custH",custH);
        m.addAttribute("menuH",menuH);
        return "menupage";
    }

    @RequestMapping(value = "/Admin", method = RequestMethod.GET)
    public String Admin(Model m) {
        m.addAttribute("MenuH",menuH);
        return "Admin";
    }
    @GetMapping("/Checkout")
    public String index(Model m) {
        m.addAttribute("custH", custH);
        m.addAttribute("coupons", coupons);
        m.addAttribute("menuH", menuH);
        return "Checkout";
    }

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String Home() {
        return "Home";
    }
    @GetMapping("/menubyid")
    @ResponseBody
    public MenuItem getMenuById(@RequestParam(name = "id") int id)
    {
        if(menuH.containsKey(id))
        {
            return menuH.get(id);
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/menu/add")
    @ResponseBody
    public Collection<MenuItem> addMenu(@RequestBody MenuItem item)
    {
        if(!menuH.containsValue(item)){
            menuH.put(menuID++, item);
            return menuH.values();
        }

       else{
        return null;
    }}

    @DeleteMapping("/menu/delete")
    @ResponseBody
    public Collection<MenuItem> deleteMenu(@RequestParam(name = "id") int id)
    {
        if( menuH.containsKey(id))
        {
            menuH.remove(id);
            return menuH.values();
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/menu/update")
    @ResponseBody
    public Collection<MenuItem> updateMenu(@RequestParam(name = "id") int id, @RequestBody MenuItem item)
    {
        if(menuH.containsKey(id))
        {
            menuH.remove(id);
            menuH.put(id, item);
            return menuH.values();
        }
        else
        {
            return null;
        }
    }
}
