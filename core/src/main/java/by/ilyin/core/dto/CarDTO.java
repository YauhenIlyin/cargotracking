package by.ilyin.core.dto;

import by.ilyin.core.entity.Car;
import lombok.Data;

@Data
public class CarDTO {

    private String number;
    private Float fuelConsumption;
    private Float loadCapacity;
    private Car.CarType carType;
    private Long clientId;

}
