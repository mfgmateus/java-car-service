package br.com.projetas.carservice.web.rest;


import br.com.projetas.carservice.service.CarService;
import br.com.projetas.carservice.service.dto.CarDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarResource {

    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok(this.carService.findAll());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.carService.findOne(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.carService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/car")
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO carDTO) throws URISyntaxException {
        CarDTO car = this.carService.create(carDTO);
        return ResponseEntity.created(new URI("api/car/" + car.getId()))
                .body(car);
    }

    @PutMapping("/car")
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(this.carService.update(carDTO));
    }


}
