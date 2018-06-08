package br.com.projetas.carservice.service.impl;

import br.com.projetas.carservice.domain.Car;
import br.com.projetas.carservice.repository.CarRepository;
import br.com.projetas.carservice.service.CarService;
import br.com.projetas.carservice.service.dto.CarDTO;
import br.com.projetas.carservice.service.mapper.CarMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarDTO create(CarDTO carDTO) {
        if (carDTO.getId() != null) {
            return this.update(carDTO);
        }
        Car car = this.carRepository.save(carMapper.toEntity(carDTO));
        return carMapper.toDto(car);
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        if (carDTO.getId() == null) {
            return this.create(carDTO);
        }
        Car car = this.carRepository.save(carMapper.toEntity(carDTO));
        return carMapper.toDto(car);
    }

    @Override
    public void delete(Long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public CarDTO findOne(Long id) {
        return carMapper.toDto(this.carRepository.findById(id).orElse(null));
    }

    @Override
    public List<CarDTO> findAll() {
        return carMapper.toDto(this.carRepository.findAll());
    }
}
