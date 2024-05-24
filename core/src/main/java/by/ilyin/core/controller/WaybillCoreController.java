package by.ilyin.core.controller;

import by.ilyin.core.dto.ReachedCheckpointInfoDTO;
import by.ilyin.core.dto.WaybillDTO;
import by.ilyin.core.dto.response.CreateWaybillResponseDTO;
import by.ilyin.core.entity.Checkpoint;
import by.ilyin.core.entity.Waybill;
import by.ilyin.core.service.WaybillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/waybills")
public class WaybillCoreController {

    private final WaybillService waybillService;

    @PostMapping
    public CreateWaybillResponseDTO createWaybill(@RequestBody WaybillDTO waybillDTO) {
        return waybillService.createWaybill(waybillDTO);
    }

    @GetMapping
    public Page<Waybill> getWaybills(Pageable pageable,
                                     @RequestParam(required = false) Set<String> carriageStatuses) {
        return waybillService.getWaybills(pageable, carriageStatuses);
    }

    @PutMapping("/{id}")
    public ReachedCheckpointInfoDTO reachCheckpoint(@PathVariable Long id) {
        return waybillService.reachCheckpoint(id);
    }

    @GetMapping("/{id}")
    public List<Checkpoint> getCheckpoints(@PathVariable Long id) {
        return waybillService.getCheckpoints(id);
    }

}
