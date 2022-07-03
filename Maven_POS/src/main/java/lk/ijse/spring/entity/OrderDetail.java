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
    private String oId;
    @Id
    private String itemId;
    private String itemKind;
    private String itemName;
    private int sellQty;
    private double unitPrice;
    private int itemDiscount;
    private double total;

    @ManyToOne
    @JoinColumn(name = "oId", referencedColumnName = "orderId",insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemCode",insertable = false,updatable = false)
    private Item item;
}
