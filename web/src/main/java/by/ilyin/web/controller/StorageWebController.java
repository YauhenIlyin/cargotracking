package by.ilyin.web.controller;

import by.ilyin.web.dto.StorageDTO;
import by.ilyin.web.dto.request.UpdateStorageRequestDTO;
import by.ilyin.web.dto.response.CreateStorageResponseDTO;
import by.ilyin.web.dto.response.GetStorageByIdResponseDTO;
import by.ilyin.web.dto.response.GetStoragesResponseDTO;
import by.ilyin.web.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storages")
public class StorageWebController {

    private final StorageService storageService;

    @PostMapping
    public CreateStorageResponseDTO createStorage(@RequestBody @Valid StorageDTO storageDTO,
                                                  BindingResult bindingResult) {
        return storageService.createStorage(storageDTO, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStorageById(@PathVariable Long id,
                                                  @RequestBody @Valid UpdateStorageRequestDTO updateStorageDTO,
                                                  BindingResult bindingResult) {
        storageService.updateStorage(id, updateStorageDTO, bindingResult);
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

    @GetMapping("/{id}")
    public GetStorageByIdResponseDTO getStorageById(@PathVariable Long id) {
        return storageService.getStorageById(id);
    }

    @GetMapping
    public GetStoragesResponseDTO getStorages(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer pageNumber) {
        return storageService.getStorages(name, pageSize, pageNumber);
    }

}
