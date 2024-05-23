package by.ilyin.web.service;

import by.ilyin.web.dto.CarDTO;
import by.ilyin.web.dto.mapper.CarDTOMapper;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateCarDTO;
import by.ilyin.web.dto.response.CreateCarResponseDTO;
import by.ilyin.web.dto.response.GetCarByIdResponseDTO;
import by.ilyin.web.dto.response.GetCarsResponseDTO;
import by.ilyin.web.entity.Car;
import by.ilyin.web.feign.CarsCoreFeignClient;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarsCoreFeignClient carsCoreFeignClient;
    private final CustomBindingResultValidator bindingResultValidator;
    private final CarDTOMapper carDTOMapper;
    @Value("${server.address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;

    public CreateCarResponseDTO createCar(CarDTO carDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        CreateCarResponseDTO createCarResponseDTO = carsCoreFeignClient.createCar(carDTO);
        String currentUrn = createCarResponseDTO.getLocation();
        StringBuilder currentUrlSB = new StringBuilder();
        currentUrlSB
                .append("http://")
                .append(serverAddress)
                .append(":")
                .append(serverPort)
                .append(currentUrn);
        createCarResponseDTO.setLocation(currentUrlSB.toString());
        return createCarResponseDTO;
    }

    public void updateCar(Long carId,
                          UpdateCarDTO updateCarDTO,
                          BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        carsCoreFeignClient.updateCar(carId, updateCarDTO);
    }

    public void deleteCars(List<Long> carIdList) {
        carsCoreFeignClient.deleteCars(carIdList);
    }

    public GetCarByIdResponseDTO getCarById(Long carId) {
        return carDTOMapper.mapToDto(carsCoreFeignClient.getCarById(carId));
    }

    public GetCarsResponseDTO getCars(Integer pageSize,
                                      Integer pageNumber,
                                      String number,
                                      Float fuelConsumptionLess,
                                      Float fuelConsumptionMore,
                                      Float loadCapacityLess,
                                      Float loadCapacityMore,
                                      Set<Car.CarType> carTypes) {
        PageDTO<Car> pageDTO = carsCoreFeignClient.getCars(
                pageSize,
                pageNumber,
                number,
                fuelConsumptionLess,
                fuelConsumptionMore,
                loadCapacityLess,
                loadCapacityMore,
                carTypes);
        return new GetCarsResponseDTO(
                pageDTO.getTotalElements(),
                pageDTO.getContent()
                        .stream()
                        .map(carDTOMapper::mapToDto)
                        .collect(Collectors.toList()));
    }

}
