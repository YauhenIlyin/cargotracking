package by.ilyin.core.controller;

import by.ilyin.core.dto.CarDTO;
import by.ilyin.core.dto.request.UpdateCarDTO;
import by.ilyin.core.dto.response.CreateCarResponseDTO;
import by.ilyin.core.entity.Car;
import by.ilyin.core.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarCoreController {

    private final CarService carService;

    @PostMapping
    public CreateCarResponseDTO createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable Long id,
                                          @RequestBody UpdateCarDTO updateCarDTO) {
        carService.updateCar(id, updateCarDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCars(@RequestBody List<Long> carIdList) {
        carService.deleteCars(carIdList);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    public Page<Car> getCars(@RequestParam(required = false) String number,
                             @RequestParam(required = false) Float fuelConsumptionLess,
                             @RequestParam(required = false) Float fuelConsumptionMore,
                             @RequestParam(required = false) Float loadCapacityLess,
                             @RequestParam(required = false) Float loadCapacityMore,
                             @RequestParam(required = false) Set<Car.CarType> carTypes,
                             Pageable pageable) {
        return carService.getCars(
                number,
                fuelConsumptionLess,
                fuelConsumptionMore,
                loadCapacityLess,
                loadCapacityMore,
                carTypes,
                pageable);
    }

}
