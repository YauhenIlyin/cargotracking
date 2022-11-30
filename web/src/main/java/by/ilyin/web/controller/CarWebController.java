package by.ilyin.web.controller;

import by.ilyin.web.dto.CarDTO;
import by.ilyin.web.dto.request.UpdateCarDTO;
import by.ilyin.web.dto.response.CreateCarResponseDTO;
import by.ilyin.web.dto.response.GetCarByIdResponseDTO;
import by.ilyin.web.dto.response.GetCarsResponseDTO;
import by.ilyin.web.entity.Car;
import by.ilyin.web.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarWebController {

    private final CarService carService;

    @PostMapping
    public CreateCarResponseDTO createCar(@RequestBody @Valid CarDTO carDTO,
                                          BindingResult bindingResult) {
        return carService.createCar(carDTO, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable Long id,
                                          @RequestBody @Valid UpdateCarDTO updateCarDTO,
                                          BindingResult bindingResult) {
        carService.updateCar(id, updateCarDTO, bindingResult);
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
    public GetCarByIdResponseDTO getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    public GetCarsResponseDTO getCars(@RequestParam(required = false) Integer pageSize,
                                      @RequestParam(required = false) Integer pageNumber,
                                      @RequestParam(required = false) String number,
                                      @RequestParam(required = false) Float fuelConsumptionLess,
                                      @RequestParam(required = false) Float fuelConsumptionMore,
                                      @RequestParam(required = false) Float loadCapacityLess,
                                      @RequestParam(required = false) Float loadCapacityMore,
                                      @RequestParam(required = false) Set<Car.CarType> carTypes) {
        return carService.getCars(
                pageSize,
                pageNumber,
                number,
                fuelConsumptionLess,
                fuelConsumptionMore,
                loadCapacityLess,
                loadCapacityMore,
                carTypes);
    }

}
