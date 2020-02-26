package psu.edu.restaurant;

import java.util.Vector;

public class CustomerDTO
{
    String name;
    Vector<MenuItem> cart;

    public CustomerDTO(Customer cust)
    {
        this.name = cust.getName();
        this.cart = cust.getCart();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<MenuItem> getCart() {
        return cart;
    }

    public void setCart(Vector<MenuItem> cart) {
        this.cart = cart;
    }
}
