package by.ilyin.web.feign;

import by.ilyin.web.dto.StorageDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateStorageRequestDTO;
import by.ilyin.web.dto.response.CreateStorageResponseDTO;
import by.ilyin.web.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "storageCoreFeignClient", url = "${feign.client.core.url}")
public interface StorageCoreFeignClient {

    @PostMapping(value = "/api/storages", consumes = "application/json")
    CreateStorageResponseDTO createStorage(StorageDTO storageDTO);

    @PutMapping(value = "/api/storages/{id}", consumes = "application/json")
    void updateStorageById(@PathVariable Long id,
                           UpdateStorageRequestDTO updateStorageDTO);


    @DeleteMapping(value = "/api/storages", consumes = "application/json")
    void deleteStorages(List<Long> storageIdList);

    @GetMapping(value = "/api/storages/{id}")
    Storage getStorageById(@PathVariable Long id);

    @GetMapping(value = "/api/storages")
    PageDTO<Storage> getStorages(@RequestParam String name,
                                 @RequestParam Integer size,
                                 @RequestParam Integer page);

}
