package by.ilyin.web.feign;

import by.ilyin.web.dto.ClientDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateClientDTO;
import by.ilyin.web.dto.response.CreateClientResponseDTO;
import by.ilyin.web.entity.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "clientsCoreFeignClient", url = "${feign.client.core.url}")
public interface ClientsCoreFeignClient {

    @PostMapping(value = "/api/clients", consumes = "application/json")
    Long createClient(ClientDTO clientDTO); //todo add clients path builder

    @PutMapping(value = "/api/clients/{id}", consumes = "application/json")
    void updateClient(@PathVariable("id") Long clientId, UpdateClientDTO updateClientDTO);

    @GetMapping(value = "/api/clients/{id}", consumes = "application/json")
    Client getClientById(@PathVariable("id") Long clientId);

    @GetMapping(value = "/api/clients", consumes = "application/json")
    PageDTO<Client> getClients(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Client.ClientStatus status,
                               @RequestParam(required = false) Integer pageSize,
                               @RequestParam(required = false) Integer pageNumber);

    @PutMapping(value = "/api/clients/activate/{clientId}", consumes = "application/json")
    void activateClient(@PathVariable Long clientId);

    @DeleteMapping(value = "/api/clients", consumes = "application/json")
    void deleteClients(List<Long> clientIdList);

}
