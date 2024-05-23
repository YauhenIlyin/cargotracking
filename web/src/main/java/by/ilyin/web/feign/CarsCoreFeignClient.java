package by.ilyin.web.feign;

import by.ilyin.web.dto.CarDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateCarDTO;
import by.ilyin.web.dto.response.CreateCarResponseDTO;
import by.ilyin.web.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "carsCoreFeignClient", url = "${feign.client.core.url}")
public interface CarsCoreFeignClient {

    @PostMapping(value = "/api/cars", consumes = "application/json")
    CreateCarResponseDTO createCar(CarDTO carDTO);

    @PutMapping(value = "/api/cars/{id}", consumes = "application/json")
    void updateCar(@PathVariable Long id, UpdateCarDTO updateCarDTO);

    @DeleteMapping(value = "/api/cars", consumes = "application/json")
    void deleteCars(List<Long> carIdList);

    @GetMapping(value = "/api/cars/{id}")
    Car getCarById(@PathVariable Long id);

    @GetMapping(value = "/api/cars")
    PageDTO<Car> getCars(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) Float fuelConsumptionLess,
            @RequestParam(required = false) Float fuelConsumptionMore,
            @RequestParam(required = false) Float loadCapacityLess,
            @RequestParam(required = false) Float loadCapacityMore,
            @RequestParam(required = false) Set<Car.CarType> carTypes);
}
