package by.ilyin.web.service;

import by.ilyin.web.dto.StorageDTO;
import by.ilyin.web.dto.mapper.StorageDTOMapper;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateStorageRequestDTO;
import by.ilyin.web.dto.response.CreateStorageResponseDTO;
import by.ilyin.web.dto.response.GetStorageByIdResponseDTO;
import by.ilyin.web.dto.response.GetStoragesResponseDTO;
import by.ilyin.web.entity.Storage;
import by.ilyin.web.feign.StorageCoreFeignClient;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final CustomBindingResultValidator bindingResultValidator;
    private final StorageCoreFeignClient storageCoreFeignClient;
    private final StorageDTOMapper storageDTOMapper;

    public CreateStorageResponseDTO createStorage(StorageDTO storageDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        return storageCoreFeignClient.createStorage(storageDTO);
    }

    public void updateStorage(Long storageId,
                              UpdateStorageRequestDTO updateStorageRequestDTO,
                              BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        storageCoreFeignClient.updateStorageById(storageId, updateStorageRequestDTO);
    }

    public void deleteStorages(List<Long> storageIdList) {
        storageCoreFeignClient.deleteStorages(storageIdList);
    }

    public GetStorageByIdResponseDTO getStorageById(Long storageId) {
        return storageDTOMapper.mapToDto(storageCoreFeignClient.getStorageById(storageId));
    }

    public GetStoragesResponseDTO getStorages(String name, Integer pageSize, Integer pageNumber) {
        PageDTO<Storage> pageDTO = storageCoreFeignClient.getStorages(name, pageSize, pageNumber);
        return new GetStoragesResponseDTO(
                pageDTO.getContent()
                        .stream()
                        .map(storageDTOMapper::mapToDto)
                        .collect(Collectors.toList()),
                pageDTO.getTotalElements()
        );
    }

}
