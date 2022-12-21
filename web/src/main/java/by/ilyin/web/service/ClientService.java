package by.ilyin.web.service;

import by.ilyin.web.dto.ClientDTO;
import by.ilyin.web.dto.mapper.ClientDTOMapper;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateClientDTO;
import by.ilyin.web.dto.response.GetClientByIdResponseDTO;
import by.ilyin.web.dto.response.GetClientsResponseDTO;
import by.ilyin.web.entity.Client;
import by.ilyin.web.feign.ClientsCoreFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientsCoreFeignClient clientsCoreFeignClient;
    private final ClientDTOMapper clientDTOMapper;

    public ResponseEntity<URI> createClient(ClientDTO clientDTO) {
        Long clientId = clientsCoreFeignClient.createClient(clientDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientId).toUri();
        return ResponseEntity.ok(location);
    }

    public void updateClient(Long clientId, UpdateClientDTO updateClientDTO) {
        clientsCoreFeignClient.updateClient(clientId, updateClientDTO);
    }

    public GetClientByIdResponseDTO getClientById(Long clientId) {
        return clientDTOMapper.mapToDto(clientsCoreFeignClient.getClientById(clientId));
    }

    public GetClientsResponseDTO getClients(String name,
                                            Client.ClientStatus status,
                                            Integer pageSize,
                                            Integer pageNumber) {
        PageDTO<Client> pageDTO = clientsCoreFeignClient.getClients(name, status, pageSize, pageNumber);
        List<GetClientByIdResponseDTO> simpleClientList =
                pageDTO.getContent()
                        .stream()
                        .map(clientDTOMapper::mapToDto)
                        .collect(Collectors.toList());
        return new GetClientsResponseDTO(simpleClientList, pageDTO.getTotalElements());
    }

    public void activateClient(Long clientId) {
        clientsCoreFeignClient.activateClient(clientId);
    }

    public void deleteClients(List<Long> clientIdList) {
        clientsCoreFeignClient.deleteClients(clientIdList);
    }

}