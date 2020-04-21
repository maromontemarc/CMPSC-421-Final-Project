package psu.edu.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public Collection<MenuItem> getMenu()
    {
        return menuH.values();

    }

    @RequestMapping(value = "/menupage", method = RequestMethod.GET)
    public String menupage() {
        return "menupage";
    }


    @GetMapping("/menubyid")
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
