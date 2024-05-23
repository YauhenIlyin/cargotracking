package by.ilyin.web.controller;

import by.ilyin.web.dto.ClientDTO;
import by.ilyin.web.dto.request.UpdateClientDTO;
import by.ilyin.web.dto.response.GetClientByIdResponseDTO;
import by.ilyin.web.dto.response.GetClientsResponseDTO;
import by.ilyin.web.entity.Client;
import by.ilyin.web.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientWebController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<URI> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable("id") Long clientId,
                                             @RequestBody @Valid UpdateClientDTO updateClientDTO) {
        clientService.updateClient(clientId, updateClientDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public GetClientByIdResponseDTO getClientById(@PathVariable("id") Long clientId) {
        return clientService.getClientById(clientId);
    }

    @GetMapping
    public GetClientsResponseDTO getClients(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) Client.ClientStatus status,
                                            @RequestParam(required = false) Integer pageSize,
                                            @RequestParam(required = false) Integer pageNumber) {
        return clientService.getClients(name, status, pageSize, pageNumber);
    }

    @PutMapping("/activate/{clientId}")
    public ResponseEntity<Void> activateClient(@PathVariable Long clientId) {
        clientService.activateClient(clientId);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClients(@RequestBody List<Long> clientIdList) {
        clientService.deleteClients(clientIdList);
        return ResponseEntity
                .ok()
                .build();
    }

}
