package testServiceImpl;

import lk.ijse.spring.config.WebAppConfig;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class})
@ExtendWith(SpringExtension.class)
@Transactional
public class testCustomerServiceImpl {

    @Autowired
    CustomerService customerService;

    @Test
    void saveCustomer(){
        CustomerDTO customerDTO1 = new CustomerDTO("C00-0001", "Bihara", "female","078-5992369","995321478v","Galle", "rash@gmail.com");
        customerService.saveCustomer(customerDTO1);
        System.out.println(customerDTO1);
    }

    @Test
    void getIds(){
        System.out.println(customerService.generateCustomerIds());
    }

}
