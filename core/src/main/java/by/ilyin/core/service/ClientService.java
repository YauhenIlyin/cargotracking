package by.ilyin.core.service;

import by.ilyin.core.dto.ClientDTO;
import by.ilyin.core.dto.mapper.ClientDTOMapper;
import by.ilyin.core.dto.request.UpdateClientDTO;
import by.ilyin.core.dto.response.CreateClientResponseDTO;
import by.ilyin.core.entity.Client;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.exception.http.client.ResourceAlreadyExists;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.ClientRepository;
import by.ilyin.core.repository.CustomUserRepository;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final CustomUserRepository customUserRepository;
    private final ClientDTOMapper clientDTOMapper;
    private final CustomUserService customUserService;
    private final @Qualifier("clientFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateClientResponseDTO createClient(ClientDTO clientDTO) {
        Client client = clientDTOMapper.mapFromDto(clientDTO);
        if (clientRepository.existsById(clientDTO.getAdminInfo().getClientId())) {
            throw new ResourceAlreadyExists("Client with id " +
                    clientDTO.getAdminInfo().getClientId() + " already exists.");
        }
        if (customUserRepository.existsByLogin(clientDTO.getAdminInfo().getLogin())) {
            throw new ResourceAlreadyExists("User with login " +
                    clientDTO.getAdminInfo().getLogin() + " already exists.");
        }
        CustomUser admin = client.getGeneralAdmin();
        client.setGeneralAdmin(null);
        client.setId(clientDTO.getAdminInfo().getClientId());
        Client realClient = clientRepository.save(client);
        admin.setUserRoles(customUserService.getRealUserRoleSet(clientDTO.getAdminInfo().getUserRoles()));
        admin.setClient(realClient);
        customUserRepository.save(admin);
        client.setGeneralAdmin(admin);
        clientRepository.save(client);
        return new CreateClientResponseDTO(client.getId());
    }

    @Transactional
    public void updateClient(Long clientId, UpdateClientDTO updateClientDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " +
                        clientId + " not found."));
        client.setName(updateClientDTO.getName());
        client.setStatus(updateClientDTO.getStatus());
        clientRepository.save(client);
    }

    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " +
                        clientId + " not found."));
    }

    public Page<Client> getClients(String name, Client.ClientStatus status, Pageable pageable) {
        HashMap<String, Object> filterValues = new HashMap<>();
        filterValues.put("name", name);
        filterValues.put("status", status);
        Specification<Client> specification = takeGetClientsSpecification(filterValues);
        return clientRepository.findAll(specification, pageable);
    }

    @Transactional
    public void activateClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " +
                        clientId + " not found."));
        client.setStatus(Client.ClientStatus.LEGAL);
        clientRepository.save(client);
    }

    @Transactional
    public void deleteClients(List<Long> clientIdList) {
        List<Client> clientList = clientRepository.findAllById(clientIdList)
                .stream()
                .peek(o -> o.setDeleteDate(LocalDateTime.now()))
                .collect(Collectors.toList());
        clientRepository.saveAll(clientList);
    }

    private Specification<Client> takeGetClientsSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<Client> clientFiltrationBuilder = new FiltrationBuilder<>();
        clientFiltrationBuilder
                .addCriteria(
                        filterValues.get("name") != null,
                        "name",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("name"))
                .addCriteria(
                        filterValues.get("status") != null,
                        "status",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("status"));
        return clientFiltrationBuilder.build(fieldCriteriaTypes);
    }

}
