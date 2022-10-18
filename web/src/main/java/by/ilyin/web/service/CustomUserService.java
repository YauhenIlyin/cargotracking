package by.ilyin.web.service;

import by.ilyin.web.dto.request.*;
import by.ilyin.web.dto.response.*;
import by.ilyin.web.feign.UsersCoreFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
@Service
public class CustomUserService {

    private final UsersCoreFeignClient usersCoreFeignClient;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO, BindingResult bindingResult) {
        return usersCoreFeignClient.createUser(createUserRequestDTO);
    }

    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        return usersCoreFeignClient.updateUser(updateUserRequestDTO.getId(), updateUserRequestDTO);
    }

    public DeleteUsersResponseDTO deleteUser(DeleteUsersRequestDTO deleteUsersRequestDTO) {
        return usersCoreFeignClient.deleteUser(deleteUsersRequestDTO);
    }

    public GetUsersResponseDTO getUsers(GetUsersRequestDTO getUsersRequestDTO) {
        return usersCoreFeignClient.getUsers(
                getUsersRequestDTO.getName(),
                getUsersRequestDTO.getSurname(),
                getUsersRequestDTO.getPatronymic(),
                getUsersRequestDTO.getBeforeBornDate(),
                getUsersRequestDTO.getAfterBornDate(),
                getUsersRequestDTO.getTown(),
                getUsersRequestDTO.getStreet(),
                getUsersRequestDTO.getHouse(),
                getUsersRequestDTO.getFlat(),
                getUsersRequestDTO.getUserRoles(),
                getUsersRequestDTO.getPageSize(),
                getUsersRequestDTO.getPageNumber());
    }

    public GetUserResponseDTO getUserById(long id) {
        return usersCoreFeignClient.getUserById(id);
    }

}