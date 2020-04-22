package psu.edu.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import static psu.edu.restaurant.RestaurantController.menuH;

@Controller
public class CustomerController
{
    static public HashMap<Integer, Customer> custH = new HashMap<>();
    static public HashMap<Integer, CustomerDTO> custDTOH = new HashMap<>();
    private int custID = 0;

    public CustomerController()
    {
        Customer Marc = new Customer("Marc", "123 Home Road", "1234");
        Customer Tom = new Customer("Tom", "456 House Street", "5678");
        Customer Dave = new Customer("Dave", "789 Road Road", "9101");
        Customer Marshall = new Customer("Marshall", "119 Barn Street", "2312");

        CustomerDTO MarcDTO = new CustomerDTO(Marc);
        CustomerDTO TomDTO = new CustomerDTO(Tom);
        CustomerDTO DaveDTO = new CustomerDTO(Dave);
        CustomerDTO MarshallDTO = new CustomerDTO(Marshall);

        custH.put(custID, Marc);
        custDTOH.put(custID, MarcDTO);
        custID++;

        custH.put(custID, Tom);
        custDTOH.put(custID, TomDTO);
        custID++;

        custH.put(custID, Dave);
        custDTOH.put(custID, DaveDTO);
        custID++;

        custH.put(custID, Marshall);
        custDTOH.put(custID, MarshallDTO);
        custID++;
    }

    @GetMapping("/cust/get")
    public Customer getCustomer(@RequestParam(name = "id") int id)
    {
        if(custH.containsKey(id)) {
            return custH.get(id);
        }
        else
        {
            return null;
        }
    }

    @GetMapping("/cust/list")
    public Collection<CustomerDTO> getCustomerList()
    {
        return custDTOH.values();
    }

    @PostMapping("/cust/create")
    public String createCustomer(@RequestBody Customer cust)
    {
        if(custH.containsValue(cust)){
            return "This Customer already exists";
    }
        else {
            custH.put(custID, cust);
            CustomerDTO custDTO = new CustomerDTO(cust);
            custDTOH.put(custID, custDTO);
            custID++;

            return cust.getName();
        }
    }

    @DeleteMapping("/cust/delete")
    public Collection<Customer> deleteCustomer(@RequestParam(name = "id") int id )
    {
        if(custH.containsKey(id) && custDTOH.containsKey(id))
        {
            custH.remove(id);
            custDTOH.remove(id);
            return custH.values();
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/cust/update")
    public Customer updateCustomer(@RequestParam(name = "id") int id, @RequestBody Customer cust)
    {
        if(custH.containsKey(id) && custDTOH.containsKey(id))
        {
            custH.remove(id);
            custDTOH.remove(id);
            custH.put(id, cust);
            CustomerDTO custDTO = new CustomerDTO(cust);
            custDTOH.put(id, custDTO);

            return cust;
        }
        else
        {
            return null;
        }
    }

}
