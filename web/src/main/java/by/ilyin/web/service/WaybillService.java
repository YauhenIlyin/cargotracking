package by.ilyin.web.service;

import by.ilyin.web.controller.websocket.WebSocketController;
import by.ilyin.web.dto.ReachedCheckpointInfoDTO;
import by.ilyin.web.dto.WaybillDTO;
import by.ilyin.web.dto.mapper.CheckpointDTOMapper;
import by.ilyin.web.dto.mapper.WaybillDTOMapper;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.response.CreateWaybillResponseDTO;
import by.ilyin.web.dto.response.GetCheckpointResponseDTO;
import by.ilyin.web.dto.response.GetWaybillsResponseDTO;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.entity.Waybill;
import by.ilyin.web.feign.WaybillCoreFeignClient;
import by.ilyin.web.security.CustomUserDetails;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaybillService {

    private final CustomBindingResultValidator bindingResultValidator;
    private final WaybillCoreFeignClient waybillCoreFeignClient;
    private final WaybillDTOMapper waybillDTOMapper;
    private final CheckpointDTOMapper checkpointDTOMapper;
    private final WebSocketController webSocketController;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.address}")
    private String serverAddress;

    public CreateWaybillResponseDTO createWaybill(WaybillDTO waybillDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        CreateWaybillResponseDTO createWaybillResponseDTO = waybillCoreFeignClient.createUser(waybillDTO);
        StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(serverAddress)
                .append(":")
                .append(serverPort)
                .append(createWaybillResponseDTO.getLocation());
        createWaybillResponseDTO.setLocation(sb.toString());
        return createWaybillResponseDTO;
    }

    public GetWaybillsResponseDTO getWaybills(Integer pageNumber,
                                              Integer pageSize,
                                              Set<String> carriageStatuses) {
        PageDTO<Waybill> pageDTO = waybillCoreFeignClient.getWaybills(pageNumber, pageSize, carriageStatuses);
        return new GetWaybillsResponseDTO(
                pageDTO.getTotalElements(),
                pageDTO.getContent()
                        .stream()
                        .map(waybillDTOMapper::mapToDTO)
                        .collect(Collectors.toList())
        );
    }

    public void reachCheckpoint(Long checkPointId) {
        Long clientId = getCurrentCustomUser().getClient().getId();
        ReachedCheckpointInfoDTO reachedCheckpointInfoDTO = waybillCoreFeignClient.reachCheckpoint(checkPointId);
        webSocketController.sendReachedCheckpointInfo(clientId, reachedCheckpointInfoDTO);
    }

    private CustomUser getCurrentCustomUser() {
        return ((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getCustomUser();
    }

    public List<GetCheckpointResponseDTO> getCheckpoints(Long id) {
        return waybillCoreFeignClient.getCheckpoints(id)
                .stream()
                .map(checkpointDTOMapper::mapToDTO)
                .collect(Collectors.toList());
    }

}
