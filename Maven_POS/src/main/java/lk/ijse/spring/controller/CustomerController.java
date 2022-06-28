package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO saveCustomer(@ModelAttribute CustomerDTO cusDto){
        System.out.println(cusDto);
        return customerService.saveCustomer(cusDto);
    }

    /*@GetMapping(path = "/{id}")
    public CustomerDTO searchCustomer(@PathVariable String id){
        CustomerDTO customerDTO = new CustomerDTO(id,"","","","","","");
        return customerDTO;
    }*/

    /*@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO cusDto){
    }*/

    /*@DeleteMapping(params = {"id"})
    public CustomerDTO deleteCustomer(@RequestParam String id){
    }*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomer(){
        return customerService.getAllCustomer();
    }

}
