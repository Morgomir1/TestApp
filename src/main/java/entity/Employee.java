package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
public class Employee {

    private int id;
    private String name;
    private String surname;
    private LocalDateTime birthDate;
    private String department;

}
