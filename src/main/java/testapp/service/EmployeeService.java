package testapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import testapp.entity.Employee;
import testapp.exceptions.EmployeeNotFoundException;
import testapp.repository.EmployeeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

    public Employee findById(Statement stmt, int id) {
        try {
            ResultSet set = stmt.executeQuery("SELECT * FROM testapp.employee where id=" + id);
            if (set.next())
            return new Employee(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> groupByName(Statement stmt) {
        try {
            ResultSet set = stmt.executeQuery("SELECT name FROM testapp.employee group by name");
            List<String> names = new ArrayList<>();
            while (set.next()) {
                names.add(set.getString("name"));
            }
            return names;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> findBetween(Statement stmt, LocalDate min, LocalDate max) {
        try {
            ResultSet set = stmt.executeQuery(String.format("SELECT * FROM testapp.employee where (birth_date >='%s') and (birth_date <='%s')", min, max));
            List<Employee> employees = new ArrayList<>();
            while (set.next()) {
                employees.add(new Employee(set));
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
