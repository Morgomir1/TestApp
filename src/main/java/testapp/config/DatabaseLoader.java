package testapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import testapp.entity.Employee;
import testapp.repository.EmployeeRepository;
import testapp.service.EmployeeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Configuration
public class DatabaseLoader {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);
    public static String pattern = "dd-MM-yyyy";
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

    @Bean
    public EmployeeService employeeBean() {
        return new EmployeeService();
    }

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        return args -> {
            logger.info("Preloading " + repository.save(new Employee(1, "Egor", "Timoshinov", LocalDate.parse("02-07-2002", dateTimeFormatter), "development", 40000)));
            logger.info("Preloading " + repository.save(new Employee(2, "Ivan", "Timoshinov", LocalDate.parse("02-07-2002", dateTimeFormatter), "development", 40000)));
        };
    }
}
