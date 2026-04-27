package de.ghostnet.ghostnet.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name  ="reporting_person")
public class ReportingPerson {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phoneNumber;

    private boolean anonymous;
}
