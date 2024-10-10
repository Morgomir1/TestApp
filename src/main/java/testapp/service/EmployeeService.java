package testapp.service;

import testapp.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
