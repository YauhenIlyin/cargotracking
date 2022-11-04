package by.ilyin.web.service;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.dto.PageDTO;
import by.ilyin.web.dto.mapper.CustomUserDTOMapper;
import by.ilyin.web.dto.request.*;
import by.ilyin.web.dto.response.*;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.entity.UserRole;
import by.ilyin.web.feign.UsersCoreFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserService {

    private final UsersCoreFeignClient usersCoreFeignClient;
    private final CustomUserDTOMapper customUserDTOMapper;
    @Value("${server.address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;

    public CreateUserResponseDTO createUser(CustomUserDTO customUserDTO) {
        CreateUserResponseDTO createUserResponseDTO = usersCoreFeignClient.createUser(customUserDTO);
        String currentUrn = createUserResponseDTO.getCurrentUserURI();
        StringBuilder currentUrlSB = new StringBuilder();
        currentUrlSB
                .append("https://")
                .append(serverAddress)
                .append(":")
                .append(serverPort)
                .append(currentUrn);
        createUserResponseDTO.setCurrentUserURI(currentUrlSB.toString());
        return createUserResponseDTO;
    }

    public UpdateUserResponseDTO updateUser(long id, UpdateUserRequestDTO updateUserRequestDTO) {
        return usersCoreFeignClient.updateUser(id, updateUserRequestDTO);
    }

    public DeleteUsersResponseDTO deleteUser(List<Long> userIdList) {
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
                                        List<String> userRoles,
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
            currentUserDto = customUserDTOMapper.mapUserToDTO(currentUser);
            currentUserDto.setUserRoles(convertRolesSetToTypeList(currentUser.getUserRoles()));
            userDTOList.add(currentUserDto);
        }
        GetUsersResponseDTO getUsersResponseDTO = new GetUsersResponseDTO();
        getUsersResponseDTO.setContent(userDTOList);
        getUsersResponseDTO.setTotalElements(pageDTO.getTotalElements());
        return getUsersResponseDTO;
    }

    public CustomUserDTO getUserById(long id) {
        CustomUser realUser = usersCoreFeignClient.getUserById(id);
        CustomUserDTO userDto = customUserDTOMapper.mapUserToDTO(realUser);
        userDto.setUserRoles(convertRolesSetToTypeList(realUser.getUserRoles()));
        return userDto;
    }

    private List<UserRole.UserRoleType> convertRolesSetToTypeList(Set<UserRole> userRolesSet) {
        List<UserRole.UserRoleType> userRoleTypeList = null;
        if (userRolesSet != null) {
            userRoleTypeList = new ArrayList<>();
            for (UserRole currentUserRole : userRolesSet) {
                userRoleTypeList.add(currentUserRole.getRoleType());
            }
        }
        return userRoleTypeList;
    }

}
