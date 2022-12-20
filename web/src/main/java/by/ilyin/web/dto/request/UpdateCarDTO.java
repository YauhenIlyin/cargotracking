package by.ilyin.web.dto.request;

import by.ilyin.web.entity.Car;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateCarDTO {

    @NotNull(message = "Number must be not null.")
    @Size(max = 10, message = "Number must be no more than 10 characters long.")
    private String number;
    @NotNull(message = "Fuel consumption must be not null.")
    private Float fuelConsumption;
    @NotNull(message = "Load capacity must be not null.")
    private Float loadCapacity;
    @NotNull(message = "Car type must be not null.")
    private Car.CarType carType;

}
