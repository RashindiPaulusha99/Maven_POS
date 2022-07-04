package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Orders, String> {

    @Query(value = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1", nativeQuery = true)
    String generateOrderId();

    @Query(value = "SELECT COUNT(*) FROM Orders", nativeQuery = true)
    int countOrders();

    @Query(value = "SELECT SUM(netTotal) FROM Orders", nativeQuery = true)
    double calculateIncome();
}
