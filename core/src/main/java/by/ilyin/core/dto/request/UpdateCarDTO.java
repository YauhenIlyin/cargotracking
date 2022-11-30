package by.ilyin.core.dto.request;

import by.ilyin.core.entity.Car;
import lombok.Data;

@Data
public class UpdateCarDTO {

    private String number;
    private Float fuelConsumption;
    private Float loadCapacity;
    private Car.CarType carType;

}
