package psu.edu.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static psu.edu.restaurant.CouponController.coupons;
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
    void testGetMenuByIDHappy2()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem item = rc.getMenuById(1);
        assertEquals(item.getItem(), "Burger");
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
    void testAddMenuItemUnhappy()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem burger =menuH.get(1);

            assertEquals(null,rc.addMenu(burger));

    }
    @Test
    void testAddMenuItemHappy2()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem CokeA = new MenuItem("Coke A",2.5 );
        rc.addMenu(CokeA);
        assertTrue(menuH.containsValue(CokeA));
    }
    @Test
    void testDeleteMenuItemHappy()
    {
        RestaurantController rc = new RestaurantController();
        rc.deleteMenu(1);
        assertEquals(null,rc.getMenuById(1));
    }
    @Test
    void testDeleteMenuItemHappy2()
    {
        RestaurantController rc = new RestaurantController();
        rc.deleteMenu(2);
        assertEquals(null,rc.getMenuById(2));
    }
    @Test
    void testDeleteMenuItemUnhappy()
    {
        RestaurantController rc = new RestaurantController();
        rc.deleteMenu(30);
        assertEquals(null,  rc.deleteMenu(30));
    }

    @Test
    void testUpdateMenuItemH()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem Pop = new MenuItem("Pop",4.15 );
        rc.updateMenu(1,Pop);
        assertEquals(Pop,rc.getMenuById(1));
    }
    @Test
    void testUpdateMenuItemHappy2()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem CokeA = new MenuItem("Coke A",2.5 );
        rc.updateMenu(2,CokeA);
        assertEquals(CokeA,rc.getMenuById(2));
    }

    @Test
    void testUpdateMenuItemUnhappy()
    {
        RestaurantController rc = new RestaurantController();
        MenuItem Pop = new MenuItem("Pop",4.15 );

        assertEquals(null, rc.updateMenu(30,Pop));
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
    void testGetCustHappy2()
    {
        CustomerController cc = new CustomerController();
        Customer cust = cc.getCustomer(1);
        assertEquals(cust.getName(), "Tom");
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
    void testUpdateCustomerHappy2()
    {
        CustomerController cc = new CustomerController();
        Customer John = new Customer("Jeff","1533 penn ave","544-2234-12231");
        cc.updateCustomer(2,John );
        assertEquals(John,cc.getCustomer(2));
    }
    @Test
    void testUpdateCustomerUnhappy()
    {
        CustomerController cc = new CustomerController();
        Customer Jeff = new Customer("Jeff","1533 penn ave","544-2234-12231");
        cc.updateCustomer(10,Jeff );
        Assertions.assertNull(cc.updateCustomer(10,Jeff ));
    }

    @Test
    void testDeleteCustomerHappy()
    {
        CustomerController cc = new CustomerController();
        cc.deleteCustomer(1);
        assertEquals(null,cc.getCustomer(1));
    }

    @Test
    void testDeleteCustomerHappy2()
    {
        CustomerController cc = new CustomerController();
        cc.deleteCustomer(0);
        assertEquals(null,cc.getCustomer(0));
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
    void testCreateCustomerHappy2()
    {
        CustomerController cc = new CustomerController();
        Customer Jeff = new Customer("Jeff","1533 penn ave","544-2234-12231");


        assertEquals(Jeff.name, cc.createCustomer(Jeff));
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
    void testUpdateCouponHappy2()
    {
        CouponController cc = new CouponController();
        Coupon dif = new Coupon("50% off total",.8);
        cc.updateCoupon(2,dif);
        assertEquals(dif,cc.getCouponById(2));
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
    void testDeleteCouponHappy2()
    {
        CouponController cc = new CouponController();
        cc.deleteCoupon(2);
        assertEquals(null,cc.getCouponById(2));
    }

    @Test
    void testDeleteCouponUnHappy()
    {
        CouponController cc = new CouponController();
        cc.deleteCoupon(1);
        assertEquals(null,cc.getCouponById(1));
    }

    @Test
    void testGetCouponByIdHappy()
    {
        CouponController cc = new CouponController();
        assertNotEquals(null,cc.getCouponById(1));
    }
    @Test
    void testGetCouponByIdHappy2()
    {
        CouponController cc = new CouponController();
        assertNotEquals(null,cc.getCouponById(1));
    }
    @Test
    void testGetCouponByIdUnHappy()
    {
        CouponController cc = new CouponController();
        assertEquals(null,cc.getCouponById(30));
    }

    @Test
    void testCouponAddHappy()
    {
        CouponController cc = new CouponController();
        Coupon r = new Coupon("20%", .2);
        assertNotEquals(null,cc.addCoupon(r));
    }
    @Test
    void testCouponAddHappy2()
    {
        CouponController cc = new CouponController();
        Coupon d = new Coupon("70%", .7);
        assertNotEquals(null,cc.addCoupon(d));
    }
    @Test
    void testCouponAddUnHappy()
    {
        CouponController cc = new CouponController();
        Coupon r = coupons.get(1);

        assertEquals("Coupon already exists",cc.addCoupon(r));
    }

  //  @Test
//    void testCouponList()
//    {
//        CouponController cc = new CouponController();
//        Collection<Coupon> coupon = cc.getCoupons();
//        for(int i = 0; i < coupons.size(); i++)
//        {
//            if(!coupon.contains(coupons.get(i)))
//            {
//                System.out.println("Error: doesn't contain coupon " + coupons.get(i).name );
//            }
//            else
//            {
//                System.out.println("Contains: " + coupons.get(i).name );
//            }
//        }
//    }

    // CartController tests
    //
    @Test
    void testAddToCartHappy()
    {
        CartController cc = new CartController();
        RestaurantController rc = new RestaurantController();
        CustomerController customerController = new CustomerController();
        assertNotEquals(null,  cc.addToCart(1,1));
    }

    @Test
    void testAddToCartHappy2()
    {
        CartController cc = new CartController();

        assertNotEquals(null,  cc.addToCart(2,0));
    }
    @Test
    void testAddToCartUnHappy()
    {
        CartController cc = new CartController();

        assertEquals(null,  cc.addToCart(10,1));
    }
    @Test
    void testClearCartHappy()
    {
        CartController cc = new CartController();
        CustomerController cb = new CustomerController();
        cc.addToCart(0, 0);
        cc.clearCart(0);

        Assertions.assertEquals(cb.getCustomer(1).cart, cb.getCustomer(0).cart);
    }
    @Test
    void testClearCartHappy2()
    {
        CartController cc = new CartController();
        CustomerController cb = new CustomerController();
        cc.addToCart(1, 1);
        cc.clearCart(1);

        Assertions.assertEquals(cb.getCustomer(0).cart, cb.getCustomer(1).cart);
    }
    @Test
    void testClearCartUnhappy()
    {
        CartController cc = new CartController();
        Assertions.assertNull(cc.clearCart(10));
    }

    @Test
    void testDeleteCartHappy()
    {
        CartController cc = new CartController();
        cc.addToCart(1,1);
        cc.addToCart(2,1);

        assertNotEquals(null,  cc.removeFromCart(2,1));
    }
    @Test
    void testDeleteCartHappy2()
    {
        CartController cc = new CartController();
        cc.addToCart(0,0);
        cc.addToCart(1,0);

        assertNotEquals(null,  cc.removeFromCart(1,0));
    }

    @Test
    void testDeleteCartUnHappy()
    {
        CartController cc = new CartController();

        assertEquals(null,  cc.removeFromCart(30,30));
    }

    @Test
    void testCheckOutHappy()
    {
        CartController cc = new CartController();
        CustomerController customerController = new CustomerController();
        RestaurantController restaurantController = new RestaurantController();
        cc.addToCart(0, 0);
        Assertions.assertEquals(12, cc.checkOut(0, "Pickup", -1));
    }
    @Test
    void testCheckOutHappy2()
    {
        CartController cc = new CartController();
        CustomerController customerController = new CustomerController();
        RestaurantController restaurantController = new RestaurantController();
        cc.addToCart(0, 0);
        Assertions.assertEquals(14, cc.checkOut(0, "Delivery", -1));
    }
    @Test
    void testCheckOutUnhappy()
    {
        CartController cc = new CartController();
        CustomerController customerController = new CustomerController();
        RestaurantController restaurantController = new RestaurantController();
        cc.addToCart(0, 0);
        Assertions.assertEquals(-1, cc.checkOut(-1, "Delivery", -1));
    }

}
