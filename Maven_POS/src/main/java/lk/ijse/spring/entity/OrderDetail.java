package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@IdClass(OrderItem.class)
public class OrderDetail {

    @Id
    private String orderId;
    @Id
    private String itemCode;
    private String itemKind;
    private String itemName;
    private int sellQty;
    private double unitPrice;
    private int itemDiscount;
    private double total;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId",insertable = false, updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode",insertable = false,updatable = false)
    private Item item;

}
