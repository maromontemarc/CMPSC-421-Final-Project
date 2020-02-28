package psu.edu.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
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
    void testDeleteCustomerUnHappy()
    {
        CustomerController cc = new CustomerController();
        assertEquals(null,cc.deleteCustomer(10));
    }

    @Test
    void testCreateCustomerHappy()
    {
        CustomerController cc = new CustomerController();
        Customer John = new Customer("john","1533 penn ave","544-2234-12231");


        assertEquals(John.name, cc.createCustomer(John));
    }
    @Test
    void testCreateCustomerUnhappy()
    {
        CustomerController cc = new CustomerController();
        Customer John = cc.custH.get(1);
        String exp ="This Customer already exists";
        assertEquals(exp, cc.createCustomer(John));
    }

    // CouponController tests
    //
    @Test
    void testUpdateCouponHappy()
    {
        CouponController cc = new CouponController();
        Coupon dif = new Coupon("50% off total",.3);
        cc.updateCoupon(1,dif);
        assertEquals(dif,cc.getCouponById(1));
    }
    @Test
    void testUpdateCouponUnHappy()
    {
        CouponController cc = new CouponController();
        Coupon dif = new Coupon("50% off total",.3);
        String expected = "this coupon does not exist.";
        assertEquals(expected, cc.updateCoupon(10,dif));
    }

    @Test
    void testDeleteCouponHappy()
    {
        CouponController cc = new CouponController();
        cc.deleteCoupon(1);
        assertEquals(null,cc.getCouponById(1));
    }

    @Test
    void testDeleteCouponUnHappy()
    {
        CouponController cc = new CouponController();
        cc.deleteCoupon(1);
        assertEquals(null,cc.getCouponById(1));
    }

    // CartController tests
    //
    @Test
    void testAddToCartHappy()
    {
        CartController cc = new CartController();
        RestaurantController rc = new RestaurantController();
        CustomerController cb = new CustomerController();



        assertNotEquals(null,  cc.addToCart(1,1));
    }
    @Test
    void testAddToCartUnHappy()
    {
        CartController cc = new CartController();
        RestaurantController rc = new RestaurantController();
        CustomerController cb = new CustomerController();



        assertEquals(null,  cc.addToCart(10,1));
    }
    @Test
    void testDeleteCartHappy()
    {
        CartController cc = new CartController();
        RestaurantController rc = new RestaurantController();
        CustomerController cb = new CustomerController();
        cc.addToCart(1,1);
        cc.addToCart(2,1);

        assertNotEquals(null,  cc.removeFromCart(2,1));
    }
    @Test
    void testDeleteCartUnHappy()
    {
        CartController cc = new CartController();
        RestaurantController rc = new RestaurantController();
        CustomerController cb = new CustomerController();


        assertEquals(null,  cc.removeFromCart(30,30));
    }


}
