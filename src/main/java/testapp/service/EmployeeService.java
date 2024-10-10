package testapp.service;

import testapp.entity.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private Connection connection;

    public EmployeeService(String url, String user, String password) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
        if (con != null) {
            this.connection = con;
            System.out.println("Успешное подключение к базе данных!\n");
        } else {
            System.out.println("Не удалось подключиться к базе данных.\n");
        }
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public ResultSet executeQuery(String request) throws SQLException {
        return connection.createStatement().executeQuery(request);
    }

    public Employee findById(int id) {
        try {
            ResultSet set = executeQuery("SELECT * FROM testapp.employee where id=" + id);
            if (set.next())
            return new Employee(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> groupByName() {
        try {
            ResultSet set = executeQuery("SELECT name FROM testapp.employee group by name");
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

    public List<Employee> findBetween(LocalDate min, LocalDate max) {
        try {
            ResultSet set = executeQuery(String.format("SELECT * FROM testapp.employee where (birth_date >='%s') and (birth_date <='%s')", min, max));
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
