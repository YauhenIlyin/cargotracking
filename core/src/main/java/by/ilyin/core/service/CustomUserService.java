package by.ilyin.core.service;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.dto.mapper.CustomUserDTOMapper;
import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.dto.response.*;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.UserRole;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.exception.http.client.ResourceAlreadyExists;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.CustomUserRepository;
import by.ilyin.core.repository.UserRoleRepository;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import by.ilyin.core.util.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomUserService {

    private final CustomUserRepository customUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final CustomUserDTOMapper customUserDTOMapper;
    private final UserValidator userValidator;
    private final @Qualifier("userFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateUserResponseDTO createUser(CustomUserDTO customUserDTO) {
        CustomUser customUser = customUserDTOMapper.mapFromDto(customUserDTO);
        customUser.setUserRoles(getRealUserRoleSet(customUserDTO.getUserRoles()));
        boolean isLoginExists = userValidator.isUserLoginAlreadyExists(customUserDTO.getLogin());
        if (isLoginExists) {
            throw new ResourceAlreadyExists("Login " + customUserDTO.getLogin() + " already exists.");
        }
        CustomUser realUser = customUserRepository.save(customUser);
        return new CreateUserResponseDTO(realUser.getId());
    }

    @Transactional
    public void updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
        Optional<CustomUser> currentOptionalUser = customUserRepository.findById(id);
        currentOptionalUser.orElseThrow(() -> new ResourceNotFoundException("Update failed. User with id " + id + " was not found."));
        Optional<CustomUser> optionalUserByLogin = customUserRepository.findByLogin(updateUserRequestDTO.getLogin());
        boolean isCorrect = userValidator.isCorrectLoginWithId(currentOptionalUser, optionalUserByLogin);
        if (!isCorrect) {
            throw new ResourceAlreadyExists("Login " + updateUserRequestDTO.getLogin() + " already exists for another user.");
        }
        CustomUser customUser = currentOptionalUser.get();
        Set<UserRole> realRolesSet = getRealUserRoleSet(updateUserRequestDTO.getUserRoles());
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
        if (updateUserRequestDTO.getIsChangePassword()) {
            customUser.setPassword(updateUserRequestDTO.getPassword());
        }
        customUser.setPassportNum(updateUserRequestDTO.getPassportNum());
        customUser.setIssuedBy(updateUserRequestDTO.getIssuedBy());
        customUser.setUserRoles(realRolesSet);
        customUserRepository.save(customUser);
        //todo info log
    }

    @Transactional
    public void deleteUser(List<Long> userIdList) {
        customUserRepository.deleteByIdIsIn(userIdList);
        //todo info log
    }

    public Page<CustomUser> getUsers(
            String name,
            String surname,
            String patronymic,
            String beforeBornDate,
            String afterBornDate,
            String town,
            String street,
            String house,
            String flat,
            Set<String> userRoles,
            Pageable pageable) {
        Map<String, Object> filterValues = new HashMap<>();
        if (beforeBornDate != null) {
            LocalDate localDate = LocalDate.parse(beforeBornDate);
            filterValues.put("beforeBornDate", localDate);
        }
        if (afterBornDate != null) {
            LocalDate localDate = LocalDate.parse(afterBornDate);
            filterValues.put("afterBornDate", localDate);
        }
        filterValues.put("name", name);
        filterValues.put("surname", surname);
        filterValues.put("patronymic", patronymic);
        filterValues.put("town", town);
        filterValues.put("street", street);
        filterValues.put("house", house);
        filterValues.put("flat", flat);
        if (userRoles != null && userRoles.size() > 0) {
            Set<UserRole.UserRoleType> roleTypeSet = new HashSet<>();
            UserRole.UserRoleType typeContainer;
            for (String currentUserRole : userRoles) {
                typeContainer = UserRole.UserRoleType.valueOf(currentUserRole);
                roleTypeSet.add(typeContainer);
            }
            filterValues.put("userRoles", roleTypeSet);
        }
        Specification<CustomUser> specification = takeGetUsersSpecification(filterValues);
        return customUserRepository.findAll(specification, pageable);
    }

    public CustomUser getUser(Long id) {
        Optional<CustomUser> optionalUser = customUserRepository.findById(id);
        return optionalUser.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " was not found."));
    }

    private Specification<CustomUser> takeGetUsersSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<CustomUser> userFiltrationBuilder = new FiltrationBuilder<>();
        userFiltrationBuilder
                .addCriteria(
                        filterValues.get("name") != null,
                        "name",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("name"))
                .addCriteria(
                        filterValues.get("surname") != null,
                        "surname",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("surname"))
                .addCriteria(
                        filterValues.get("patronymic") != null,
                        "patronymic",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("patronymic"))
                .addCriteria(
                        filterValues.get("beforeBornDate") != null,
                        "bornDate",
                        KeyWords.FILTER_OPERATION_MORE,
                        filterValues.get("beforeBornDate"))
                .addCriteria(
                        filterValues.get("afterBornDate") != null,
                        "bornDate",
                        KeyWords.FILTER_OPERATION_LESS,
                        filterValues.get("afterBornDate"))
                .addCriteria(
                        filterValues.get("town") != null,
                        "town",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("town"))
                .addCriteria(
                        filterValues.get("street") != null,
                        "street",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("street"))
                .addCriteria(
                        filterValues.get("house") != null,
                        "house",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("house"))
                .addCriteria(
                        filterValues.get("flat") != null,
                        "flat",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("flat"));
        Set<UserRole> realUserRoles = getRealUserRoleSet((Set<UserRole.UserRoleType>) filterValues.get("userRoles"));
        if (realUserRoles != null) {
            for (UserRole currentUserRole : realUserRoles) {
                userFiltrationBuilder.addCriteria(
                        currentUserRole != null,
                        "userRoles",
                        KeyWords.FILTER_OPERATION_EQUALS_SET_FIELD_ELEMENT,
                        currentUserRole);
            }
        }
        return userFiltrationBuilder.build(fieldCriteriaTypes);
    }

    private Set<UserRole> getRealUserRoleSet(Set<UserRole.UserRoleType> dtoUserRoles) {
        HashSet<UserRole> realRoleSet = null;
        if (dtoUserRoles != null) {
            Set<UserRole> realUserRoleList = userRoleRepository.findByRoleTypeIsIn(dtoUserRoles);
            realRoleSet = new HashSet<>(realUserRoleList);
        }
        return realRoleSet;
    }

}
