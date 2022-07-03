package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDTO {
    private String orderId;
    private CustomerDTO customer;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    private double grossTotal;
    private double netTotal;
    ArrayList<OrderDetailsDTO> orderDetails;
}
