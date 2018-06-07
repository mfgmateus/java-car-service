package br.com.projetas.sampledemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

@Data
@Entity
public class Car {

    @Id
    @SequenceGenerator(name = "sequenceGenerator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    private String brand;

    private String model;

    private String color;

    private Integer year;

    private Double price;

    private String description;

    private Boolean newCar;

    private LocalDate creationDate;

    private LocalDate updatedDate;

}
