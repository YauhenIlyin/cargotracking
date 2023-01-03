package by.ilyin.core.controller;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.service.CustomUserService;
import by.ilyin.core.util.validation.custom.ValidCreateUserData;
import by.ilyin.core.util.validation.custom.ConsistentUpdateUserParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Validated
public class UserCoreController {

    private final CustomUserService customUserService;

    @PostMapping
    public Long createUser(@RequestBody @ValidCreateUserData CustomUserDTO customUserDTO) {
        return customUserService.createUser(customUserDTO);
    }

    @PutMapping("/{id}")
    @ConsistentUpdateUserParameters
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
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
    public Page<CustomUser> getUsers(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String surname,
                                     @RequestParam(required = false) String patronymic,
                                     @RequestParam(required = false) String beforeBornDate,
                                     @RequestParam(required = false) String afterBornDate,
                                     @RequestParam(required = false) String town,
                                     @RequestParam(required = false) String street,
                                     @RequestParam(required = false) String house,
                                     @RequestParam(required = false) String flat,
                                     @RequestParam(required = false) Set<String> userRoles,
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
    public CustomUser getUserById(@PathVariable Long id) {
        return customUserService.getUser(id);
    }

}
