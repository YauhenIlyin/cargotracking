package by.ilyin.core.controller;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.dto.response.*;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserCoreController {

    private final CustomUserService customUserService;

    @PostMapping
    public CreateUserResponseDTO createUser(@RequestBody CustomUserDTO customUserDTO) {
        return customUserService.createUser(customUserDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id,
                                           @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        customUserService.updateUser(id, updateUserRequestDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody List<Long> userIdList) {
        customUserService.deleteUser(userIdList);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping
    public Page<CustomUser> getUsers(@RequestParam(required = false, value = "name") String name,
                                     @RequestParam(required = false, value = "surname") String surname,
                                     @RequestParam(required = false, value = "patronymic") String patronymic,
                                     @RequestParam(required = false, value = "beforeBornDate") String beforeBornDate,
                                     @RequestParam(required = false, value = "afterBornDate") String afterBornDate,
                                     @RequestParam(required = false, value = "town") String town,
                                     @RequestParam(required = false, value = "street") String street,
                                     @RequestParam(required = false, value = "house") String house,
                                     @RequestParam(required = false, value = "flat") String flat,
                                     @RequestParam(required = false, value = "userRoles") Set<String> userRoles,
                                     Pageable pageable) {
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
                pageable);
    }

    @GetMapping("/{id}")
    public CustomUser getUserById(@PathVariable("id") Long id) {
        return customUserService.getUser(id);
    }

}
