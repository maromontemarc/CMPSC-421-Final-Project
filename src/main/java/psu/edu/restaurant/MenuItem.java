package psu.edu.restaurant;

public class MenuItem
{
    String item;
    double price;

    MenuItem(String name, double cost)
    {
        this.item = name;
        this.price = cost;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
