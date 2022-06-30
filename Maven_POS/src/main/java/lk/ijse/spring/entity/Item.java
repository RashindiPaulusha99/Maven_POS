package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Item {
    private String customerId;
    private String customerName;
    private String gender;
    private String contact;
    private String nic;
    private String address;
    private String email;
}
