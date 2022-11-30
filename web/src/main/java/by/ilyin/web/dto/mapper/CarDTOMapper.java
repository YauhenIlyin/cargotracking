package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetCarByIdResponseDTO;
import by.ilyin.web.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarDTOMapper {

    GetCarByIdResponseDTO mapToDto(Car Car);

}
