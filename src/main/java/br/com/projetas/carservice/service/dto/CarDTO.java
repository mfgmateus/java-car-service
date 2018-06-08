package br.com.projetas.carservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

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
