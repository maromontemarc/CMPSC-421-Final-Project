package psu.edu.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertEquals(item.getItem(), "Steak");
    }

    @Test
    void testGetMenuByIDUnhappy()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem item = rc.getMenuById(-1);
        Assertions.assertNull(item);
    }

    @Test
    void testAddMenuItemHappy()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem Pop = new MenuItem("Pop",4.15 );
        rc.addMenu(Pop);
        assertTrue(menuH.containsValue(Pop));
    }

    @Test
    void testDeleteMenuItemHappy()
    {
        RestaurantController rc = new RestaurantController();
        rc.deleteMenu(1);
        assertEquals(null,rc.getMenuById(1));
    }

    @Test
    void testUpdateMenuItem()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem Pop = new MenuItem("Pop",4.15 );
        rc.updateMenu(1,Pop);
        assertEquals(Pop,rc.getMenuById(1));
    }
    // CustomerController tests
    //

    @Test
    void testGetCustHappy()
    {
        CustomerController cc = new CustomerController();
        Customer cust = cc.getCustomer(0);
        assertEquals(cust.getName(), "Marc");
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
        Collection<CustomerDTO> cust = cc.getCustomerList();
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

    @Test
    void testUpdateCustomerHappy()
    {
        CustomerController cc = new CustomerController();
        Customer John = new Customer("john","1533 penn ave","544-2234-12231");
        cc.updateCustomer(1,John );
        assertEquals(John,cc.getCustomer(1));
    }


    @Test
    void testDeleteCustomerHappy()
    {
        CustomerController cc = new CustomerController();
        cc.deleteCustomer(1);
        assertEquals(null,cc.getCustomer(1));
    }

    @Test
    void testCreateCustomerhappy()
    {
        CustomerController cc = new CustomerController();
        Customer John = new Customer("john","1533 penn ave","544-2234-12231");


        assertEquals(John.name, cc.createCustomer(John).name);
    }
    @Test
    void testCreateCustomerUnhappy()
    {
        String catJson = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(catJson, headers);

        TestRestTemplate t = new TestRestTemplate();
        ResponseEntity<String> r = t.exchange( "http://localhost:8080/cust/create", HttpMethod.POST,entity, String.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());

    }
}
