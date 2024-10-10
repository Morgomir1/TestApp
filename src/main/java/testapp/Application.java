package testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testapp.config.DatabaseLoader;
import testapp.service.EmployeeService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testapp", "root", "root");
            if (con != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Not Connected");
                return;
            }
            EmployeeService employeeService = new EmployeeService();
            Scanner scanner =  new Scanner(System.in);
            while (true) {
                System.out.println("Выберите действие:\n");
                Arrays.stream(Action.values()).forEach(action -> System.out.println(action.actionText + ": " + action.name() + "\n"));
                String actionName = scanner.nextLine();
                Action action = Action.findByName(actionName);
                if (action == null) {
                    System.out.println("Такого действия не существует, введите другое!\n");
                    continue;
                }
                boolean result = action.doAction(con, employeeService, scanner);
                System.out.println();
                if (!result) {
                    break;
                }

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    private enum Action {
        FIND_BY_ID("Найти по сотрудника по id"),
        GROUP_BY_NAME("Сгруппировать всех сотрудников"),
        FIND_BETWEEN("Найти сотрудников с датой рождения между выбранными датами"),
        EXIT("Завершить программу"),
        ;

        String actionText;

        Action(String actionText) {
            this.actionText = actionText;
        }

        public static Action findByName(String name) {
            return Arrays.stream(Action.values()).filter(action1 -> action1.name().equals(name)).findFirst().orElse(null);
        }

        public boolean doAction(Connection con, EmployeeService service, Scanner scanner) throws SQLException {
            Statement stmt = con.createStatement();
            if (this == EXIT) {
                return false;
            }
            if (this == FIND_BY_ID) {
                int id = -1;
                System.out.println("Ввведите id: \n");
                try {
                    id = Integer.parseInt(scanner.nextLine());
                    if (id < 1) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("Нужно ввести число > 1!");
                    return true;
                }
                System.out.println(service.findById(stmt, id));;
            }
            if (this == GROUP_BY_NAME) {
                System.out.println(service.groupByName(stmt));;
            }
            if (this == FIND_BETWEEN) {
                try {
                    System.out.println("Ввведите начальную дату(" + DatabaseLoader.pattern + "): \n");
                    LocalDate minDate = LocalDate.parse(scanner.nextLine(), DatabaseLoader.dateTimeFormatter);
                    System.out.println("Ввведите конечную дату(" + DatabaseLoader.pattern + "): \n");
                    LocalDate maxDate = LocalDate.parse(scanner.nextLine(), DatabaseLoader.dateTimeFormatter);
                    System.out.println(service.findBetween(stmt, minDate, maxDate));;
                } catch (DateTimeParseException e) {
                    System.out.println("Вы ввели некорректную дату, попробуйте ещё раз!");
                }
            }
            return true;
        }
    }
}
