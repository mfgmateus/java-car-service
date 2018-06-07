package br.com.projetas.sampledemo.service;

import br.com.projetas.sampledemo.service.dto.CarDTO;

import java.util.List;

public interface CarService {

    CarDTO create(CarDTO carDTO);

    CarDTO update(CarDTO carDTO);

    void delete(Long id);

    CarDTO findOne(Long id);

    List<CarDTO> findAll();

}
