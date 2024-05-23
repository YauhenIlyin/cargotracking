package by.ilyin.web.dto.response;

import by.ilyin.web.entity.Car;
import lombok.Data;

@Data
public class GetCarByIdResponseDTO {

    private Long id;
    private String number;
    private Float fuelConsumption;
    private Float loadCapacity;
    private Car.CarType carType;

}
