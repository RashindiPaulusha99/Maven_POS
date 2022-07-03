package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetailsDTO {
    private String orderId;
    private String itemId;
    private String itemKind;
    private String itemName;
    private int sellQty;
    private double unitPrice;
    private int itemDiscount;
    private double total;

}
