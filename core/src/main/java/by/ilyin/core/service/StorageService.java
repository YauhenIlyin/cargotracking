package by.ilyin.core.service;

import by.ilyin.core.dto.StorageDTO;
import by.ilyin.core.dto.mapper.StorageDTOMapper;
import by.ilyin.core.dto.request.UpdateStorageRequestDTO;
import by.ilyin.core.dto.response.CreateStorageResponseDTO;
import by.ilyin.core.entity.Client;
import by.ilyin.core.entity.Storage;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.ClientRepository;
import by.ilyin.core.repository.StorageRepository;
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

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageDTOMapper storageDTOMapper;
    private final ClientRepository clientRepository;
    private final StorageRepository storageRepository;
    private final @Qualifier("storageFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    //todo location - address / link ???
    @Transactional
    public CreateStorageResponseDTO createStorage(StorageDTO storageDTO) {
        Client client = clientRepository.findById(storageDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with id " + storageDTO.getClientId() + " not found."));
        Storage storage = storageDTOMapper.mapFromDTO(storageDTO);
        storage.setClient(client);
        storageRepository.save(storage);
        return new CreateStorageResponseDTO(storageDTO.getAddress());
    }

    @Transactional
    public void updateStorage(Long storageId, UpdateStorageRequestDTO updateStorageRequestDTO) {
        Storage currentStorage = storageRepository.findById(storageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Storage with id " + storageId + " not found."));
        currentStorage.setName(updateStorageRequestDTO.getName());
        currentStorage.setAddress(updateStorageRequestDTO.getAddress());
        storageRepository.save(currentStorage);
    }

    @Transactional
    public void deleteStorages(List<Long> storageIdList) {
        storageRepository.deleteByIdIsIn(storageIdList);
    }

    public Page<Storage> getStorages(String name, Pageable pageable) {
        Map<String, Object> filterValues = new HashMap<>();
        filterValues.put("name", name);
        Specification<Storage> storageSpecification = takeGetStoragesSpecification(filterValues);
        return storageRepository.findAll(storageSpecification, pageable);
    }

    public Storage findById(Long storageId) {
        return storageRepository.findById(storageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Storage with id " + storageId + " not found."));
    }

    private Specification<Storage> takeGetStoragesSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<Storage> storageFiltrationBuilder = new FiltrationBuilder<>();
        storageFiltrationBuilder
                .addCriteria(
                        filterValues.get("name") != null,
                        "name",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("name"));
        return storageFiltrationBuilder.build(fieldCriteriaTypes);
    }

}
