package testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import testapp.entity.Employee;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public
interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM testapp.employee where id=:employeeId")
    Optional<Employee> findById(@Param("employeeId") int employeeId);

    @Query(
            nativeQuery = true,
            value = "SELECT name FROM testapp.employee group by name")
    Optional<String> groupByName();

    @Query(
            nativeQuery = true,
            //value = "SELECT * FROM testapp.employee where id=:employeeId")
            value = "FROM testapp.employee where (birth_date >=:minDate) and (birth_date <=:maxDate)")
    Optional<Employee> findBetween(@Param("minDate") LocalDateTime min, @Param("maxDate") LocalDateTime max);
}
