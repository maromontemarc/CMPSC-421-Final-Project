package psu.edu.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static psu.edu.restaurant.CustomerController.custDTOH;
import static psu.edu.restaurant.CustomerController.custH;
import static psu.edu.restaurant.RestaurantController.menuH;

@SpringBootTest
class RestaurantApplicationTests {

    // RestaurantController tests
    //
    @Test
    void testGetMenu()
    {
        RestaurantController rc = new RestaurantController();
        Collection<MenuItem> menu = rc.getMenu();
        for(int i = 0; i < menuH.size(); i++)
        {
            if(!menu.contains(menuH.get(i)))
            {
                System.out.println("Error: doesn't contain item " + menuH.get(i).getItem() );
            }
            else
            {
                System.out.println("Contains: " + menuH.get(i).getItem() );
            }
        }
    }

    @Test
    void testGetMenuByIDHappy()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem item = rc.getMenuById(0);
        Assertions.assertEquals(item.getItem(), "Steak");
    }

    @Test
    void testGetMenuByIDUnhappy()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem item = rc.getMenuById(-1);
        Assertions.assertNull(item);
    }

    // CustomerController tests
    //

    @Test
    void testGetCustHappy()
    {
        CustomerController cc = new CustomerController();
        Customer cust = cc.getCustomer(0);
        Assertions.assertEquals(cust.getName(), "Marc");
    }

    @Test
    void testGetCustUnhappy()
    {
        CustomerController cc = new CustomerController();
        Customer cust = cc.getCustomer(-1);
        Assertions.assertNull(cust);
    }

    @Test
    void testGetCustList()
    {
        CustomerController cc = new CustomerController();
        Collection<Customer> cust = cc.getCustomerList();
        for(int i = 0; i < custH.size(); i++)
        {
            if(!cust.contains(custH.get(i)))
            {
                System.out.println("Error: doesn't contain customer " + custH.get(i).getName() );
            }
            else
            {
                System.out.println("Contains: " + custH.get(i).getName() );
            }
        }
    }
}
