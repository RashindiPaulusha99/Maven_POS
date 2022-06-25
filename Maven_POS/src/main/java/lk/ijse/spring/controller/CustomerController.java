package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    @PostMapping
    public CustomerDTO saveCustomer(@ModelAttribute CustomerDTO cusDto){
        return cusDto;
    }

    @GetMapping(path = "/{id}")
    public CustomerDTO searchCustomer(@PathVariable String id){
        CustomerDTO customerDTO = new CustomerDTO(id,"","","","","","");
        return customerDTO;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO cusDto){

    }

    @DeleteMapping(params = {"id"})
    public CustomerDTO deleteCustomer(@RequestParam String id){

    }

    @GetMapping
    public List<CustomerDTO> getAllCustomer(){

    }

}
