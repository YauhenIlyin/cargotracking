package by.ilyin.core.service;

import by.ilyin.core.dto.mapper.CustomUserDTOMapper;
import by.ilyin.core.dto.request.CreateUserRequestDTO;
import by.ilyin.core.dto.request.DeleteUserRequestDTO;
import by.ilyin.core.dto.request.GetUsersRequestDTO;
import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.dto.response.*;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.UserRole;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.repository.CustomUserRepository;
import by.ilyin.core.repository.UserRoleRepository;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.PageableBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomUserService {

    private final CustomUserRepository customUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final CustomUserDTOMapper customUserDTOMapper;
    private final @Qualifier("userFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO) {
        CustomUser customUser = customUserDTOMapper.createUserRequestDtoToCustomUser(createUserRequestDTO);
        List<UserRole> realUserRoleList = getRealUserRoles(createUserRequestDTO.getUserRoles());
        if (realUserRoleList.size() > 0) {
            customUser.setUserRoles(new HashSet<>(realUserRoleList));
        } else {
            //todo throw exception
        }
        Optional<CustomUser> optionalRealUser = Optional.of(customUserRepository.save(customUser));
        CreateUserResponseDTO createUserResponseDTO = null;
        if (optionalRealUser.isPresent()) {
            long userId = optionalRealUser.get().getId();
            createUserResponseDTO = new CreateUserResponseDTO(userId);
        } else {
            //todo throw exception
        }
        return createUserResponseDTO;
    }

    @Transactional
    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        Optional<CustomUser> optionalCustomUser;
        optionalCustomUser = customUserRepository.findCustomUserById(updateUserRequestDTO.getId());
        if (optionalCustomUser.isPresent()) {
            CustomUser customUser = optionalCustomUser.get();
            List<UserRole> realRolesList = getRealUserRoles(updateUserRequestDTO.getUserRoles());
            HashSet<UserRole> realRolesSet = new HashSet<>(realRolesList);
            customUser.setName(updateUserRequestDTO.getName());
            customUser.setSurname(updateUserRequestDTO.getSurname());
            customUser.setPatronymic(updateUserRequestDTO.getPatronymic());
            customUser.setClientId(updateUserRequestDTO.getClientId());
            customUser.setBornDate(updateUserRequestDTO.getBornDate());
            customUser.setEmail(updateUserRequestDTO.getEmail());
            customUser.setTown(updateUserRequestDTO.getTown());
            customUser.setStreet(updateUserRequestDTO.getStreet());
            customUser.setHouse(updateUserRequestDTO.getHouse());
            customUser.setFlat(updateUserRequestDTO.getFlat());
            customUser.setLogin(updateUserRequestDTO.getLogin());
            if (updateUserRequestDTO.isChangePassword()) {
                customUser.setPassword(updateUserRequestDTO.getPassword());
            }
            customUser.setPassportNum(updateUserRequestDTO.getPassportNum());
            customUser.setIssuedBy(updateUserRequestDTO.getIssuedBy());
            customUser.setUserRoles(realRolesSet);
            customUserRepository.save(customUser);
        } else {
            //todo throw exception
        }
        return new UpdateUserResponseDTO();
    }

    @Transactional
    public DeleteUserResponseDTO deleteUser(DeleteUserRequestDTO deleteUserRequestDTO) {
        customUserRepository.deleteCustomUsersByIdIsIn(deleteUserRequestDTO.getUserIdList());
        return new DeleteUserResponseDTO();
    }

    public GetUsersResponseDTO getUsers(GetUsersRequestDTO getUsersRequestDTO) {
        Pageable pageable = takeGetUsersPageable(getUsersRequestDTO);
        Specification specification = takeGetUsersSpecification(getUsersRequestDTO);
        Page page = customUserRepository.findAll(specification, pageable);
        List<CustomUser> userList = page.getContent();
        List<CreateUserRequestDTO> createUserRequestDTOList = new ArrayList<>();
        CreateUserRequestDTO createUserRequestDTO;
        for (CustomUser user : userList) {
            createUserRequestDTO = customUserDTOMapper.customUserToCreateUserRequestDto(user);
            List<UserRole.UserRoleType> userRoleTypeList;
            userRoleTypeList = convertRolesSetToRolesTypeList(user.getUserRoles());
            createUserRequestDTO.setUserRoles(userRoleTypeList);
            createUserRequestDTOList.add(createUserRequestDTO);
        }
        GetUsersResponseDTO getUsersResponseDTO = new GetUsersResponseDTO();
        getUsersResponseDTO.setContent(createUserRequestDTOList);
        getUsersResponseDTO.setTotalElements(page.getTotalElements());
        return getUsersResponseDTO;
    }

    public GetUserResponseDTO getUser(long id) {
        Optional<CustomUser> optionalUser = customUserRepository.findCustomUserById(id);
        if (optionalUser.isEmpty()) {
            //todo throw exception
        }
        CustomUser user = optionalUser.get();
        GetUserResponseDTO getUserResponseDTO = customUserDTOMapper.customUserToGetUserResponseDTO(user);
        List<UserRole.UserRoleType> userRoleTypeList;
        userRoleTypeList = convertRolesSetToRolesTypeList(user.getUserRoles());
        getUserResponseDTO.setUserRoles(userRoleTypeList);
        return getUserResponseDTO;
    }

    private Pageable takeGetUsersPageable(GetUsersRequestDTO getUsersRequestDTO) {
        return PageableBuilder.getBuilder()
                .addPageSizeNumber(getUsersRequestDTO.getPageNumber(), getUsersRequestDTO.getPageSize())
                .build();
    }

    //todo fieldNames storage
    private Specification takeGetUsersSpecification(GetUsersRequestDTO getUsersRequestDTO) {
        FiltrationBuilder filtrationBuilder = FiltrationBuilder.getBuilder()
                .addCriteria(
                        getUsersRequestDTO.getName() != null,
                        "name",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        getUsersRequestDTO.getName())
                .addCriteria(
                        getUsersRequestDTO.getSurname() != null,
                        "surname",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        getUsersRequestDTO.getSurname())
                .addCriteria(
                        getUsersRequestDTO.getPatronymic() != null,
                        "patronymic",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        getUsersRequestDTO.getPatronymic())
                .addCriteria(
                        getUsersRequestDTO.getBeforeBornDate() != null,
                        "bornDate",
                        KeyWords.FILTER_OPERATION_MORE,
                        getUsersRequestDTO.getBeforeBornDate())
                .addCriteria(
                        getUsersRequestDTO.getAfterBornDate() != null,
                        "bornDate",
                        KeyWords.FILTER_OPERATION_LESS,
                        getUsersRequestDTO.getAfterBornDate())
                .addCriteria(
                        getUsersRequestDTO.getTown() != null,
                        "town",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        getUsersRequestDTO.getTown())
                .addCriteria(
                        getUsersRequestDTO.getStreet() != null,
                        "street",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        getUsersRequestDTO.getStreet())
                .addCriteria(
                        getUsersRequestDTO.getHouse() != null,
                        "house",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        getUsersRequestDTO.getHouse())
                .addCriteria(
                        getUsersRequestDTO.getFlat() != null,
                        "flat",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        getUsersRequestDTO.getFlat());
        List<UserRole> realUserRoles = getRealUserRoles(getUsersRequestDTO.getUserRoles());
        if (realUserRoles != null && realUserRoles.size() > 0) {
            int sizeUserRoles = realUserRoles.size();
            for (int index = 0; index < sizeUserRoles; ++index) {
                filtrationBuilder.addCriteria(
                        realUserRoles.get(index) != null,
                        "userRoles",
                        KeyWords.FILTER_OPERATION_EQUALS_SET_FIELD_ELEMENT,
                        index + "",
                        realUserRoles);
            }
        }
        return filtrationBuilder.build(fieldCriteriaTypes);
    }

    private List<UserRole> getRealUserRoles(List<UserRole.UserRoleType> dtoUserRoles) {
        List<UserRole> userRoleList = null;
        if (dtoUserRoles != null) {
            userRoleList = userRoleRepository.findUserRolesByRoleTypeIsIn(dtoUserRoles);
        }
        return userRoleList;
    }

    private List<UserRole.UserRoleType> convertRolesSetToRolesTypeList(Set<UserRole> userRolesSet) {
        List<UserRole.UserRoleType> userRoleTypeList = null;
        if (userRolesSet != null) {
            userRoleTypeList = new ArrayList<>();
            for (UserRole userRole : userRolesSet) {
                userRoleTypeList.add(userRole.getRoleType());
            }
        }
        return userRoleTypeList;
    }

}