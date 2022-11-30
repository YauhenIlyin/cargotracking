package by.ilyin.web.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class Car {

    private Long id;
    private String number;
    private Float fuelConsumption;
    private Float loadCapacity;
    private Car.CarType carType;
    private Long clientId;

    public enum CarType {
        REFRIGERATOR,
        CISTERN,
        COVERED_BODY
    }

}
