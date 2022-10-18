package by.ilyin.web.feign;

import by.ilyin.web.dto.request.*;
import by.ilyin.web.dto.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usersCoreFeignClient", url = "${feign.client.core.url}")
public interface UsersCoreFeignClient {

    @PostMapping(value = "/api/users", consumes = "application/json")
    CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO);

    @PutMapping(value = "/api/users/{id}", consumes = "application/json")
    UpdateUserResponseDTO updateUser(@PathVariable("id") long id,
                                     UpdateUserRequestDTO updateUserRequestDTO);

    @DeleteMapping("/api/users")
    DeleteUsersResponseDTO deleteUser(DeleteUsersRequestDTO deleteUsersRequestDTO);

    @GetMapping("api/users")
    GetUsersResponseDTO getUsers(@RequestParam(required = false, value = "name") String name,
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
                                 @RequestParam(required = false, value = "pageNumber") String pageNumber);

    @GetMapping(value = "/api/users/{id}")
    GetUserResponseDTO getUserById(@PathVariable("id") long id);

}