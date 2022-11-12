package by.ilyin.web.controller;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.dto.request.*;
import by.ilyin.web.dto.response.*;
import by.ilyin.web.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserWebController {

    private final CustomUserService customUserService;

    @PostMapping
    public String createUser(@RequestBody @Valid CustomUserDTO customUserDTO) {
        return customUserService.createUser(customUserDTO).getCurrentUserURI();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id,
                                           @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return customUserService.updateUser(id, updateUserRequestDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody List<Long> userIdList) {
        return customUserService.deleteUser(userIdList);
    }

    @GetMapping
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
            @RequestParam(required = false, value = "userRoles") Set<String> userRoles,
            @RequestParam(required = false, value = "pageSize") Integer pageSize,
            @RequestParam(required = false, value = "pageNumber") Integer pageNumber) {
        return customUserService.getUsers(
                name,
                surname,
                patronymic,
                beforeBornDate,
                afterBornDate,
                town,
                street,
                house,
                flat,
                userRoles,
                pageSize,
                pageNumber);
    }

    @GetMapping("/{id}")
    public CustomUserDTO getUser(@PathVariable("id") Long id) {
        return customUserService.getUserById(id);
    }

}
