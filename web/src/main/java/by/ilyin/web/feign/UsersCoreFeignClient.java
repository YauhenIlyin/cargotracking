package by.ilyin.web.feign;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.*;
import by.ilyin.web.entity.CustomUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "usersCoreFeignClient", url = "${feign.client.core.url}")
public interface UsersCoreFeignClient {

    @PostMapping(value = "/api/users", consumes = "application/json")
    Long createUser(CustomUserDTO customUserDTO);

    @PutMapping(value = "/api/users/{id}", consumes = "application/json")
    ResponseEntity<Void> updateUser(@PathVariable("id") Long id,
                                    UpdateUserRequestDTO updateUserRequestDTO);

    @DeleteMapping("/api/users")
    ResponseEntity<Void> deleteUser(List<Long> userIdList);

    @GetMapping("/api/users")
    PageDTO<CustomUser> getUsers(@RequestParam(required = false, value = "name") String name,
                                 @RequestParam(required = false, value = "surname") String surname,
                                 @RequestParam(required = false, value = "patronymic") String patronymic,
                                 @RequestParam(required = false, value = "beforeBornDate") String beforeBornDate,
                                 @RequestParam(required = false, value = "afterBornDate") String afterBornDate,
                                 @RequestParam(required = false, value = "town") String town,
                                 @RequestParam(required = false, value = "street") String street,
                                 @RequestParam(required = false, value = "house") String house,
                                 @RequestParam(required = false, value = "flat") String flat,
                                 @RequestParam(required = false, value = "userRoles") Set<String> userRoles,
                                 @RequestParam(required = false, value = "size") Integer size,
                                 @RequestParam(required = false, value = "page") Integer page
    );

    @GetMapping(value = "/api/users/{id}")
    CustomUser getUserById(@PathVariable("id") Long id);

}
