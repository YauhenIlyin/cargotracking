package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.CarDTO;
import by.ilyin.core.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarDTOMapper {

    Car mapFromDto(CarDTO carDTO);

}
