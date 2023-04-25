package springexamples.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springexamples.database.entity.Employee;
import springexamples.database.entity.User;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    @Query("FROM Employee e")
    List<Employee> getAllEmployees();

    // Spring can infer queries based on method names
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    List<Employee> findByFirstNameContainingOrLastNameContainingIgnoreCase(String firstName, String lastName);


    @Query(value = "SELECT * FROM employees WHERE firstname LIKE %:firstName% OR lastname LIKE %:lastName% ;", nativeQuery = true)  // Uses SQL Conventions
    List<Employee> usingNativeQuery(String firstName, String lastName);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:firstName% OR e.lastName LIKE %:lastName% ")    // Uses Employee object
    List<Employee> usingJPAQuery(String firstName, String lastName);          // Don't need setParam with Spring

    @Query(value="select e.*, o.city from employees e, offices o where e.office_id = o.id ;", nativeQuery = true)
    List<Map<String, Object>> findAllWithOfficeName();

    Employee findById(Integer id);

    boolean existsByEmail(String email);
    Employee findByEmail(String email);
}
