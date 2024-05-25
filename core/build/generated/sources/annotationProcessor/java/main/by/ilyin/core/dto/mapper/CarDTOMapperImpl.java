package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.CarDTO;
import by.ilyin.core.entity.Car;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:43+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CarDTOMapperImpl implements CarDTOMapper {

    @Override
    public Car mapFromDto(CarDTO carDTO) {
        if ( carDTO == null ) {
            return null;
        }

        Car car = new Car();

        car.setNumber( carDTO.getNumber() );
        car.setFuelConsumption( carDTO.getFuelConsumption() );
        car.setLoadCapacity( carDTO.getLoadCapacity() );
        car.setCarType( carDTO.getCarType() );

        return car;
    }
}
