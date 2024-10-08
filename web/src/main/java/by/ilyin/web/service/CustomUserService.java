package by.ilyin.web.service;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.mapper.CustomUserDTOMapper;
import by.ilyin.web.dto.request.*;
import by.ilyin.web.dto.response.*;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.entity.UserRole;
import by.ilyin.web.feign.UsersCoreFeignClient;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserService {

    private final UsersCoreFeignClient usersCoreFeignClient;
    private final CustomUserDTOMapper customUserDTOMapper;
    private final CustomBindingResultValidator customBindingResultValidator;
    @Value("${server.address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;

    public CreateUserResponseDTO createUser(CustomUserDTO customUserDTO, BindingResult bindingResult) {
        customBindingResultValidator.validationProcess(bindingResult);
        CreateUserResponseDTO createUserResponseDTO = usersCoreFeignClient.createUser(customUserDTO);
        String currentUrn = createUserResponseDTO.getCurrentUserURI();
        StringBuilder currentUrlSB = new StringBuilder();
        currentUrlSB
                .append("http://")
                .append(serverAddress)
                .append(":")
                .append(serverPort)
                .append(currentUrn);
        createUserResponseDTO.setCurrentUserURI(currentUrlSB.toString());
        return createUserResponseDTO;
    }

    public ResponseEntity<Void> updateUser(Long id,
                                           UpdateUserRequestDTO updateUserRequestDTO,
                                           BindingResult bindingResult) {
        customBindingResultValidator.validationProcess(bindingResult);
        return usersCoreFeignClient.updateUser(id, updateUserRequestDTO);
    }

    public ResponseEntity<Void> deleteUser(List<Long> userIdList) {
        return usersCoreFeignClient.deleteUser(userIdList);
    }

    public GetUsersResponseDTO getUsers(String name,
                                        String surname,
                                        String patronymic,
                                        String beforeBornDate,
                                        String afterBornDate,
                                        String town,
                                        String street,
                                        String house,
                                        String flat,
                                        Set<String> userRoles,
                                        Integer pageSize,
                                        Integer pageNumber) {
        PageDTO<CustomUser> pageDTO = usersCoreFeignClient.getUsers(
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
        List<CustomUser> realUserList = pageDTO.getContent();
        List<CustomUserDTO> userDTOList = new ArrayList<>();
        CustomUserDTO currentUserDto;
        for (CustomUser currentUser : realUserList) {
            currentUserDto = customUserDTOMapper.mapToDto(currentUser);
            currentUserDto.setUserRoles(convertRoleSetToTypeSet(currentUser.getUserRoles()));
            userDTOList.add(currentUserDto);
        }
        GetUsersResponseDTO getUsersResponseDTO = new GetUsersResponseDTO();
        getUsersResponseDTO.setContent(userDTOList);
        getUsersResponseDTO.setTotalElements(pageDTO.getTotalElements());
        return getUsersResponseDTO;
    }

    public CustomUserDTO getUserById(Long id) {
        CustomUser realUser = usersCoreFeignClient.getUserById(id);
        CustomUserDTO userDto = customUserDTOMapper.mapToDto(realUser);
        userDto.setUserRoles(convertRoleSetToTypeSet(realUser.getUserRoles()));
        return userDto;
    }

    public Set<UserRole.UserRoleType> convertRoleSetToTypeSet(Set<UserRole> userRoleSet) {
        Set<UserRole.UserRoleType> userRoleTypeSet = null;
        if (userRoleSet != null) {
            userRoleTypeSet = new HashSet<>();
            for (UserRole currentUserRole : userRoleSet) {
                userRoleTypeSet.add(currentUserRole.getRoleType());
            }
        }
        return userRoleTypeSet;
    }

}
