package by.ilyin.core.controller;

import by.ilyin.core.dto.request.CreateUserRequestDTO;
import by.ilyin.core.dto.request.DeleteUserRequestDTO;
import by.ilyin.core.dto.request.GetUsersRequestDTO;
import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.dto.response.*;
import by.ilyin.core.entity.UserRole;
import by.ilyin.core.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserCoreController {

    private final CustomUserService customUserService;

    @PostMapping("")
    public CreateUserResponseDTO createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        return customUserService.createUser(createUserRequestDTO);
    }

    //todo long id ?
    @PutMapping("/{id}")
    public UpdateUserResponseDTO updateUser(@PathVariable("id") long id,
                                            @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return customUserService.updateUser(updateUserRequestDTO);
    }

    @DeleteMapping("")
    public DeleteUserResponseDTO deleteUsers(@RequestBody DeleteUserRequestDTO deleteUserRequestDTO) {
        return customUserService.deleteUser(deleteUserRequestDTO);
    }

    @GetMapping("")
    public GetUsersResponseDTO getUsers(@RequestParam(required = false, value = "name") String name,
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
        if (beforeBornDate != null) {
            getUsersRequestDTO.setBeforeBornDate(LocalDate.parse(beforeBornDate));
        }
        if (afterBornDate != null) {
            getUsersRequestDTO.setAfterBornDate(LocalDate.parse(afterBornDate));
        }
        getUsersRequestDTO.setTown(town);
        getUsersRequestDTO.setStreet(street);
        getUsersRequestDTO.setHouse(house);
        getUsersRequestDTO.setFlat(flat);
        if (userRoles != null) {
            List<UserRole.UserRoleType> roleTypeList = new ArrayList<>();
            UserRole.UserRoleType typeContainer;
            for (int index = 0; index < userRoles.length; ++index) {
                typeContainer = UserRole.UserRoleType.valueOf(userRoles[index]);
                roleTypeList.add(typeContainer);
            }
            getUsersRequestDTO.setUserRoles(roleTypeList);
        }
        if (pageSize != null) {
            getUsersRequestDTO.setPageSize(Integer.parseInt(pageSize));
        }
        if (pageNumber != null) {
            getUsersRequestDTO.setPageNumber(Integer.parseInt(pageNumber));
        }
        return customUserService.getUsers(getUsersRequestDTO);
    }

    @GetMapping("/{id}")
    public GetUserResponseDTO getUserById(@PathVariable("id") long id) {
        return customUserService.getUser(id);
    }

}