package by.ilyin.core.controller;

import by.ilyin.core.dto.ClientDTO;
import by.ilyin.core.dto.request.UpdateClientDTO;
import by.ilyin.core.dto.response.CreateClientResponseDTO;
import by.ilyin.core.entity.Client;
import by.ilyin.core.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientCoreController {

    private final ClientService clientService;

    @PostMapping
    public CreateClientResponseDTO createClient(@RequestBody ClientDTO clientDTO) {
        System.out.println(clientDTO);
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable("id") Long clientId,
                                             @RequestBody UpdateClientDTO updateClientDTO) {
        clientService.updateClient(clientId, updateClientDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Long clientId) {
        return clientService.getClientById(clientId);
    }

    @GetMapping
    public Page<Client> getClients(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) Client.ClientStatus status,
                                   Pageable pageable) {
        return clientService.getClients(name, status, pageable);
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
