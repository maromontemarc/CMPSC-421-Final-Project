package psu.edu.restaurant;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

@RestController
public class RestaurantController
{
    static public HashMap<Integer, String> menuH = new HashMap<>();
    private int menuID = 0;
    public RestaurantController()
    {
        menuH.put(menuID++, "Steak: $12");
        menuH.put(menuID++, "Burger: $8");
        menuH.put(menuID++, "Cheeseburger: $9");
        menuH.put(menuID++, "Pizza: $10");
        menuH.put(menuID++, "Grilled Chicken: $9");
    }

    @GetMapping("/menu")
    public Collection<String> getMenu()
    {
        return menuH.values();
    }

    @GetMapping("/menubyid")
    public String getMenuById(@RequestParam(name = "id") int id)
    {
        return menuH.get(id);
    }

    @PostMapping("/menu/add")
    public String addMenu(@RequestBody String item)
    {
        menuH.put(menuID++, item);
        return item;
    }

    @DeleteMapping("/menu/delete")
    public int deleteMenu(@RequestParam(name = "id") int id)
    {
        menuH.remove(id);
        return id;
    }

    @PutMapping("/menu/update")
    public String updateMenu(@RequestParam(name = "id") int id, @RequestBody String item)
    {
        menuH.remove(id);
        menuH.put(id, item);
        return item;
    }
}
