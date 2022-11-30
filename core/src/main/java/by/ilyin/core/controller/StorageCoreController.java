package by.ilyin.core.controller;

import by.ilyin.core.dto.StorageDTO;
import by.ilyin.core.dto.request.UpdateStorageRequestDTO;
import by.ilyin.core.dto.response.CreateStorageResponseDTO;
import by.ilyin.core.entity.Storage;
import by.ilyin.core.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storages")
public class StorageCoreController {

    private final StorageService storageService;

    @PostMapping
    public CreateStorageResponseDTO createStorage(@RequestBody StorageDTO storageDTO) {
        return storageService.createStorage(storageDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStorageById(@PathVariable Long id,
                                                  @RequestBody UpdateStorageRequestDTO updateStorageDTO) {
        storageService.updateStorage(id, updateStorageDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStorages(@RequestBody List<Long> storageIdList) {
        storageService.deleteStorages(storageIdList);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping
    public Page<Storage> getStorages(@RequestParam(required = false) String name,
                                     Pageable pageable) {
        return storageService.getStorages(name, pageable);
    }

    @GetMapping("/{id}")
    public Storage getStorageById(@PathVariable Long id) {
        return storageService.findById(id);
    }

}
