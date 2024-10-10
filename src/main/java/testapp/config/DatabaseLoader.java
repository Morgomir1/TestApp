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
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


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
        Random random = new Random();
        random.setSeed(123L);
        AtomicInteger id = new AtomicInteger();
        return args -> {
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Egor", "Timoshinov", LocalDate.parse("02-07-2002", dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Ivan", "Timoshinov", LocalDate.parse("02-07-2002", dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Evgeniu", "Evgeniuov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Petr", "Petrov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Vasiliy", "Vasiliyov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Aleksei", "Alekseiov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Aleksandr", "Aleksandrov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Timofei", "Timofeiov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Matvey", "Matveyov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Sergey", "Sergeyov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
            logger.info("Preloading " + repository.save(new Employee(id.getAndIncrement(), "Goydamir", "Goydamirov", LocalDate.parse(randomBirthDate(random), dateTimeFormatter), "development", 40000 + random.nextInt(5000))));
        };
    }

    private String randomBirthDate(Random random) {
        int day = random.nextInt(28) + 1;
        int month = random.nextInt(12) + 1;
        return (day < 10 ? "0" + day : day) + "-" + (month < 10 ? "0" + month : month) + "-" + (1990 + random.nextInt( 12));
    }
}
