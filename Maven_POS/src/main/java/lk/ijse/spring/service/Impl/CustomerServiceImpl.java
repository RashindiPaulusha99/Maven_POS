package lk.ijse.spring.service.Impl;

import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public void saveCustomer(Customer customer) {
        if (!customerRepo.existsById(customer.getCusId())){
            customerRepo.save(customer);
        }else {
            throw new RuntimeException(customer.getCusId() + " " + "Customer Already Exists..!");
        }
    }

    public void updateCustomer(Customer customer) {
        if (customerRepo.existsById(customer.getCusId())){
            customerRepo.save(customer);
        }else {
            throw new RuntimeException(customer.getCusId() + " " + "No Such Customer..! Please Check The Correct Id..!");
        }
    }

    public void deleteCustomer(String id) {
        if (customerRepo.existsById(id)){
            customerRepo.deleteById(id);
        }else {
            throw new RuntimeException(id + " " + "No Such Customer..! Please Check The Correct Id..!");
        }
    }

    public Customer searchCustomer(String id) {
        if (customerRepo.existsById(id)){
            return customerRepo.findById(id).get();
        }else {
            throw new RuntimeException(id + " " + "No Such Customer..! Please Check The Id..!");
        }
    }

    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }
}
