package lk.ijse.spring.repo;

import lk.ijse.spring.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail,String> {
}
