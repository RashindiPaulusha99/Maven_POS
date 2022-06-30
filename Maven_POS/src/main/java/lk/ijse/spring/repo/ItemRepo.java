package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepo extends JpaRepository<Item, String> {
    @Query(value = "SELECT itemCode FROM Item ORDER BY itemCode DESC LIMIT 1", nativeQuery = true)
    String generateItemCode();
}
