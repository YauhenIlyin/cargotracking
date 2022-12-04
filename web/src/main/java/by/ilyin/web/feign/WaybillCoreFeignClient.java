package by.ilyin.web.feign;

import by.ilyin.web.dto.WaybillDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.response.CreateWaybillResponseDTO;
import by.ilyin.web.dto.response.GetCheckpointResponseDTO;
import by.ilyin.web.entity.Checkpoint;
import by.ilyin.web.entity.Waybill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "waybillCoreFeignClient", url = "${feign.client.core.url}")
public interface WaybillCoreFeignClient {

    @PostMapping(value = "/api/waybills", consumes = "application/json")
    CreateWaybillResponseDTO createUser(WaybillDTO waybillDTO);

    @GetMapping(value = "/api/waybills")
    PageDTO<Waybill> getWaybills(@RequestParam Integer page,
                                 @RequestParam Integer size,
                                 @RequestParam Set<String> carriageStatuses);

    @PutMapping(value = "/api/waybills/{id}")
    void reachCheckpoint(@PathVariable Long id);

    @GetMapping(value = "/api/waybills/{id}", consumes = "application/json")
    public List<Checkpoint> getCheckpoints(@PathVariable Long id);

}
