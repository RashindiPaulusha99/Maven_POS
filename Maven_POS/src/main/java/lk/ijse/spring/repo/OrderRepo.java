package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order, String> {

    @Query(value = "SELECT orderId FROM 'Order' ORDER BY orderId DESC LIMIT 1", nativeQuery = true)
    String generateOrderId();
}
