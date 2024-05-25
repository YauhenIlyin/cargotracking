package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetCarByIdResponseDTO;
import by.ilyin.web.entity.Car;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CarDTOMapperImpl implements CarDTOMapper {

    @Override
    public GetCarByIdResponseDTO mapToDto(Car Car) {
        if ( Car == null ) {
            return null;
        }

        GetCarByIdResponseDTO getCarByIdResponseDTO = new GetCarByIdResponseDTO();

        getCarByIdResponseDTO.setId( Car.getId() );
        getCarByIdResponseDTO.setNumber( Car.getNumber() );
        getCarByIdResponseDTO.setFuelConsumption( Car.getFuelConsumption() );
        getCarByIdResponseDTO.setLoadCapacity( Car.getLoadCapacity() );
        getCarByIdResponseDTO.setCarType( Car.getCarType() );

        return getCarByIdResponseDTO;
    }
}
