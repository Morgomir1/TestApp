package testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Scanner scanner =  new Scanner(System.in);
        while (true) {
            System.out.println("Choose:");
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
