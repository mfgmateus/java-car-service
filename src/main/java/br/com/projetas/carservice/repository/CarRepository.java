package br.com.projetas.carservice.repository;

import br.com.projetas.carservice.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
