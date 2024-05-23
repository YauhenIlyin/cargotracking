package by.ilyin.web.controller;

import by.ilyin.web.dto.WaybillDTO;
import by.ilyin.web.dto.response.CreateWaybillResponseDTO;
import by.ilyin.web.dto.response.GetCheckpointResponseDTO;
import by.ilyin.web.dto.response.GetWaybillsResponseDTO;
import by.ilyin.web.service.WaybillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/waybills")
@RequiredArgsConstructor
public class WaybillWebController {

    private final WaybillService waybillService;

    @PostMapping
    public CreateWaybillResponseDTO createWaybill(@RequestBody @Valid WaybillDTO waybillDTO,
                                                  BindingResult bindingResult) {
        return waybillService.createWaybill(waybillDTO, bindingResult);
    }

    @GetMapping
    public GetWaybillsResponseDTO getWaybills(@RequestParam(required = false) Integer pageNumber,
                                              @RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Set<String> carriageStatuses) {
        return waybillService.getWaybills(pageNumber, pageSize, carriageStatuses);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> reachCheckpoint(@PathVariable Long id) {
        waybillService.reachCheckpoint(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public List<GetCheckpointResponseDTO> getCheckpoints(@PathVariable Long id) {
        return waybillService.getCheckpoints(id);
    }


}
