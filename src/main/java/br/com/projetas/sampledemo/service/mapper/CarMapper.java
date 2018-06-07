package br.com.projetas.sampledemo.service.mapper;

import br.com.projetas.sampledemo.domain.Car;
import br.com.projetas.sampledemo.service.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarDTO, Car> {
}
