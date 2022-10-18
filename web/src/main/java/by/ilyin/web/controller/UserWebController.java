package by.ilyin.web.controller;

import by.ilyin.web.dto.request.*;
import by.ilyin.web.dto.response.*;
import by.ilyin.web.exception.CustomUserWebException;
import by.ilyin.web.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserWebController {

    private final CustomUserService customUserService;

    @PostMapping("")
    public String createUser(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO,
                             BindingResult bindingResult) throws CustomUserWebException {
        CreateUserResponseDTO createUserResponseDTO = customUserService.createUser(createUserRequestDTO, bindingResult);
        return createUserResponseDTO.getCurrentUserURI();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        updateUserRequestDTO.setId(id);
        UpdateUserResponseDTO updateUserResponseDTO = customUserService.updateUser(updateUserRequestDTO);
        return updateUserResponseDTO.getResultStr();
    }

    @DeleteMapping("")
    public String deleteUser(@RequestBody DeleteUsersRequestDTO deleteUsersRequestDTO) {
        DeleteUsersResponseDTO deleteUsersResponseDTO = customUserService.deleteUser(deleteUsersRequestDTO);
        return deleteUsersResponseDTO.getResultStr();
    }

    //todo ddos attack case?
    //todo validate size of array ?
    @GetMapping("")
    public GetUsersResponseDTO getUsers(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "surname") String surname,
            @RequestParam(required = false, value = "patronymic") String patronymic,
            @RequestParam(required = false, value = "beforeBornDate") String beforeBornDate,
            @RequestParam(required = false, value = "afterBornDate") String afterBornDate,
            @RequestParam(required = false, value = "town") String town,
            @RequestParam(required = false, value = "street") String street,
            @RequestParam(required = false, value = "house") String house,
            @RequestParam(required = false, value = "flat") String flat,
            @RequestParam(required = false, value = "userRoles") String[] userRoles,
            @RequestParam(required = false, value = "pageSize") String pageSize,
            @RequestParam(required = false, value = "pageNumber") String pageNumber) {
        GetUsersRequestDTO getUsersRequestDTO = new GetUsersRequestDTO();
        getUsersRequestDTO.setName(name);
        getUsersRequestDTO.setSurname(surname);
        getUsersRequestDTO.setPatronymic(patronymic);
        getUsersRequestDTO.setBeforeBornDate(beforeBornDate);
        getUsersRequestDTO.setAfterBornDate(afterBornDate);
        getUsersRequestDTO.setTown(town);
        getUsersRequestDTO.setStreet(street);
        getUsersRequestDTO.setHouse(house);
        getUsersRequestDTO.setFlat(flat);
        getUsersRequestDTO.setUserRoles(userRoles);
        getUsersRequestDTO.setPageSize(pageSize);
        getUsersRequestDTO.setPageNumber(pageNumber);
        return customUserService.getUsers(getUsersRequestDTO);
    }

    @GetMapping("/{id}")
    public GetUserResponseDTO getUser(@PathVariable("id") long id) {
        return customUserService.getUserById(id);
    }

}