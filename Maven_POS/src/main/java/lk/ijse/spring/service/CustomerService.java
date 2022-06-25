package lk.ijse.spring.service;

import lk.ijse.spring.entity.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(String id);

    Customer searchCustomer(String id);

    List<Customer>  getAllCustomer();
}
