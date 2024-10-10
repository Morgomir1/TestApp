package testapp.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(int id) {
        super("Employee with id: " + id + " does not exists!");
    }
}
