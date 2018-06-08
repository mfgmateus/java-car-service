package br.com.projetas.carservice.service.mapper;

import br.com.projetas.carservice.domain.Car;
import br.com.projetas.carservice.service.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarDTO, Car> {
}
