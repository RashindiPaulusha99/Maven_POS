package lk.ijse.spring.service.Impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;

    public void saveCustomer(CustomerDTO customer) {
        if (!customerRepo.existsById(customer.getCustomerId())){
            customerRepo.save(mapper.map(customer, Customer.class));
        }else {
            throw new RuntimeException(customer.getCustomerId() + " " + "Customer Already Exists..!");
        }
    }

    public void updateCustomer(CustomerDTO customer) {
        if (customerRepo.existsById(customer.getCustomerId())){
            customerRepo.save(mapper.map(customer, Customer.class));
        }else {
            throw new RuntimeException(customer.getCustomerId() + " " + "No Such Customer..! Please Check The Correct Id..!");
        }
    }

    public void deleteCustomer(String id) {
        if (customerRepo.existsById(id)){
            customerRepo.deleteById(id);
        }else {
            throw new RuntimeException(id + " " + "No Such Customer..! Please Check The Correct Id..!");
        }
    }

    public CustomerDTO searchCustomer(String id) {
        if (customerRepo.existsById(id)){
            Customer customer = customerRepo.findById(id).get();
            return mapper.map(customer, CustomerDTO.class);
        }else {
            throw new RuntimeException(id + " " + "No Such Customer..! Please Check The Id..!");
        }
    }

    public List<CustomerDTO> getAllCustomer() {
        List<Customer> all = customerRepo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>(){
        }.getType());
    }

    public String generateCustomerIds(){
        return customerRepo.generateCustomerId();
    }
}
