package testapp.service;

import org.springframework.web.bind.annotation.RestController;
import testapp.entity.Employee;
import testapp.exceptions.EmployeeNotFoundException;
import testapp.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeService {

    private final EmployeeRepository repository;

    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee findById(int id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public List<Employee> groupByName() {
        return repository.findAll().stream().sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());
    }

    public List<Employee> findBetween(LocalDateTime min, LocalDateTime max) {
        return repository.findAll().stream().filter(employee -> (employee.getBirthDate().isAfter(min) && employee.getBirthDate().isBefore(max))).collect(Collectors.toList());
    }


}
