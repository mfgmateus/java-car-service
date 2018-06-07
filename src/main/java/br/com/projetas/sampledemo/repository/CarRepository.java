package br.com.projetas.sampledemo.repository;

import br.com.projetas.sampledemo.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
