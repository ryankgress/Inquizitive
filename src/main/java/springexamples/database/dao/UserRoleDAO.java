package springexamples.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springexamples.database.entity.User;
import springexamples.database.entity.UserRole;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserId(Integer id);
}
