package lk.ijse.spring.service;

import lk.ijse.spring.dto.OrderDTO;

import java.util.List;

public interface PlaceOrderService {
    void placeOrder(OrderDTO orderDTO);
    void deleteOrder(String oid);
    void updateOrder(OrderDTO orderDTO);
    OrderDTO searchOrder(String oid);
    List<OrderDTO> getAllOrders();
    String generateOrderId();
    int countOrders();
    double calculateIncome();
}
