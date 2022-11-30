package by.ilyin.core.service;

import by.ilyin.core.dto.CarDTO;
import by.ilyin.core.dto.mapper.CarDTOMapper;
import by.ilyin.core.dto.request.UpdateCarDTO;
import by.ilyin.core.dto.response.CreateCarResponseDTO;
import by.ilyin.core.entity.Car;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.CarRepository;
import by.ilyin.core.repository.ClientRepository;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final CarDTOMapper carDTOMapper;
    private final @Qualifier("carFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateCarResponseDTO createCar(CarDTO carDTO) {
        clientRepository.findById(carDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with id " + carDTO.getClientId() + " not found."));
        Car car = carRepository.save(carDTOMapper.mapFromDto(carDTO));
        return new CreateCarResponseDTO(car.getId());
    }

    @Transactional
    public void updateCar(Long carId, UpdateCarDTO updateCarDTO) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Car with id " + carId + " not found."));
        car.setNumber(updateCarDTO.getNumber());
        car.setFuelConsumption(updateCarDTO.getFuelConsumption());
        car.setLoadCapacity(updateCarDTO.getLoadCapacity());
        car.setCarType(updateCarDTO.getCarType());
        carRepository.save(car);
    }

    @Transactional
    public void deleteCars(List<Long> carIdList) {
        carRepository.deleteByIdIsIn(carIdList);
    }

    public Car getCarById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Car with id " + carId + " not found."));
    }

    public Page<Car> getCars(String number,
                             Float fuelConsumptionLess,
                             Float fuelConsumptionMore,
                             Float loadCapacityLess,
                             Float loadCapacityMore,
                             Set<Car.CarType> carTypes,
                             Pageable pageable) {
        Map<String, Object> filterValues = new HashMap<>();
        filterValues.put("number", number);
        filterValues.put("fuelConsumptionLess", fuelConsumptionLess);
        filterValues.put("fuelConsumptionMore", fuelConsumptionMore);
        filterValues.put("loadCapacityLess", loadCapacityLess);
        filterValues.put("loadCapacityMore", loadCapacityMore);
        filterValues.put("carTypes", carTypes);
        Specification<Car> specification = takeGetUsersSpecification(filterValues);
        return carRepository.findAll(specification, pageable);
    }

    private Specification<Car> takeGetUsersSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<Car> carFiltrationBuilder = new FiltrationBuilder<>();
        carFiltrationBuilder
                .addCriteria(
                        filterValues.get("number") != null,
                        "number",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("number"))
                .addCriteria(
                        filterValues.get("fuelConsumptionLess") != null,
                        "fuelConsumption",
                        KeyWords.FILTER_OPERATION_MORE,
                        filterValues.get("fuelConsumptionLess"))
                .addCriteria(
                        filterValues.get("fuelConsumptionMore") != null,
                        "fuelConsumption",
                        KeyWords.FILTER_OPERATION_LESS,
                        filterValues.get("fuelConsumptionMore"))
                .addCriteria(
                        filterValues.get("loadCapacityLess") != null,
                        "loadCapacity",
                        KeyWords.FILTER_OPERATION_MORE,
                        filterValues.get("loadCapacityLess"))
                .addCriteria(
                        filterValues.get("loadCapacityMore") != null,
                        "loadCapacity",
                        KeyWords.FILTER_OPERATION_LESS,
                        filterValues.get("loadCapacityMore"));
        Set<Car.CarType> carTypeSet = (Set<Car.CarType>) filterValues.get("carTypes");
        if (carTypeSet != null) {
            for (Car.CarType currentCarType : carTypeSet) {
                carFiltrationBuilder.addCriteria(
                        currentCarType != null,
                        "carType",
                        KeyWords.FILTER_OPERATION_EQUALS_SET_FIELD_ELEMENT,
                        currentCarType);
            }
        }
        return carFiltrationBuilder.build(fieldCriteriaTypes);
    }

}
