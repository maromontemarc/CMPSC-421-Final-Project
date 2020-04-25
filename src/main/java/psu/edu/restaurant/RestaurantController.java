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
    static public HashMap<Integer,String> images = new HashMap<>();
    private int menuID = 0;
    private int imgID =0;
    public RestaurantController()
    {
        images.put(imgID++,"steak.png");
        images.put(imgID++,"hamburger412.jpg");
        images.put(imgID++,"cheeseburger412.jpg");
        images.put(imgID++,"pizza421.png");
        images.put(imgID++,"chicken421.jpg");

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

    @GetMapping("/menu/images")
    @ResponseBody
    public HashMap<Integer,String> getImages()
    {
        return images;
    }

    @PostMapping("/menu/images/add")
    @ResponseBody
    public String addImage(@RequestBody String image) {
        images.put(imgID++,image);
        return images.get(imgID);
    }

    @PutMapping("/menu/images/update")
    @ResponseBody
    public String updateImage(@RequestParam(name = "id") int id, @RequestBody String image)
    {
        if(images.containsKey(id))
        {
            images.remove(id);
            images.put(id, image);
            return images.get(id);
        }
        else
        {
            return null;
        }
    }
    @RequestMapping(value = "/menupage", method = RequestMethod.GET)
    public String menupage(Model m) {
        m.addAttribute("custH",custH);
        m.addAttribute("menuH",menuH);
        m.addAttribute("images",images);
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
    public HashMap<Integer,MenuItem> deleteMenu(@RequestParam(name = "id") int id)
    {
        if( menuH.containsKey(id))
        {
            images.remove(id);
            menuH.remove(id);

            int x=id;
            while(x<menuID) {
                MenuItem a=menuH.get(x+1);
                String img = images.get(x+1);
                images.put(x,img);
                menuH.put(x,a);
                x++;


            }
            imgID--;
            menuID--;
            return menuH;

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
