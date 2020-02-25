package psu.edu.restaurant;

import java.util.Vector;

public class  Customer
{
    String name, address, ccn;
    Vector<MenuItem> cart = new Vector<>();

    public Customer(String name, String address, String ccn)
    {
        this.name = name;
        this.address = address;
        this.ccn = ccn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public Vector<MenuItem> getCart() {
        return cart;
    }

    public void setCart(MenuItem item) {
        this.cart.addElement(item);
    }
}
