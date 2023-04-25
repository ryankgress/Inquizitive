package springexamples.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springexamples.database.entity.Office;

import java.util.List;

public interface OfficeDAO extends JpaRepository<Office, Long> {
    @Query("FROM Office o ORDER BY o.city ASC")
    List<Office> getAllOffices();
}
