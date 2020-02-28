package psu.edu.restaurant;


import java.util.HashMap;


public class Coupon {
    String name;
    double discount;

    public Coupon(String name, double discount){
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
