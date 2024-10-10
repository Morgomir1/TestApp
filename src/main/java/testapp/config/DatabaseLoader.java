package testapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import testapp.entity.Employee;
import testapp.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class DatabaseLoader {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        return args -> {
            logger.info("Preloading " + repository.save(new Employee(1, "Egor", "Timoshinov", LocalDateTime.parse("02-07-2002 00:00:00", dateTimeFormatter), "development", 40000)));
            logger.info("Preloading " + repository.save(new Employee(2, "Ivan", "Timoshinov", LocalDateTime.parse("02-07-2002 00:00:00", dateTimeFormatter), "development", 40000)));
        };
    }
}
