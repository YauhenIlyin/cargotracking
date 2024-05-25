package by.ilyin.web.controller;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.dto.request.*;
import by.ilyin.web.dto.response.*;
import by.ilyin.web.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserWebController {

    private final CustomUserService customUserService;

    @PostMapping
    public CreateUserResponseDTO createUser(@RequestBody @Valid CustomUserDTO customUserDTO,
                                            BindingResult bindingResult) {
        return customUserService.createUser(customUserDTO, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
                                           @RequestBody @Valid UpdateUserRequestDTO updateUserRequestDTO,
                                           BindingResult bindingResult) {
        return customUserService.updateUser(id, updateUserRequestDTO, bindingResult);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody List<Long> userIdList) {
        return customUserService.deleteUser(userIdList);
    }

    @GetMapping
    public GetUsersResponseDTO getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String patronymic,
            @RequestParam(required = false) String beforeBornDate,
            @RequestParam(required = false) String afterBornDate,
            @RequestParam(required = false) String town,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String house,
            @RequestParam(required = false) String flat,
            @RequestParam(required = false) Set<String> userRoles,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber) {
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
    public CustomUserDTO getUser(@PathVariable Long id) {
        return customUserService.getUserById(id);
    }

}
