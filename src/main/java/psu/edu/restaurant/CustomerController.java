package psu.edu.restaurant;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import static psu.edu.restaurant.RestaurantController.menuH;

@RestController
public class CustomerController
{
    static public HashMap<Integer, Customer> custH = new HashMap<>();
    static public HashMap<Integer, CustomerDTO> custDTOH = new HashMap<>();
    private int custID = 0;

    @GetMapping("/cust/get")
    public Customer getCustomer(@RequestParam(name = "id") int id)
    {
        return custH.get(id);
    }

    @GetMapping("/custList")
    public Collection<Customer> getCustomerList()
    {
        return custH.values();
    }

    @PostMapping("/cust/create")
    public CustomerDTO createCustomer(@RequestBody Customer cust)
    {
        custH.put(custID, cust);
        CustomerDTO custDTO = new CustomerDTO(cust);
        custDTOH.put(custID, custDTO);
        custID++;

        return custDTO;
    }

    @DeleteMapping("/cust/delete")
    public int deleteCustomer(@RequestParam(name = "id") int id )
    {
        custH.remove(id);
        custDTOH.remove(id);
        return id;
    }

    @PutMapping("/cust/update")
    public Customer updateCustomer(@RequestParam(name = "id") int id, @RequestBody Customer cust)
    {
        custH.remove(id);
        custDTOH.remove(id);
        custH.put(id, cust);
        CustomerDTO custDTO = new CustomerDTO(cust);
        custDTOH.put(id, custDTO);

        return cust;
    }


}
