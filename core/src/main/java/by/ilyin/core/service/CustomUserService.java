package by.ilyin.core.service;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.dto.mapper.CustomUserDTOMapper;
import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.dto.response.*;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.UserRole;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.repository.CustomUserRepository;
import by.ilyin.core.repository.UserRoleRepository;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final FiltrationBuilder<CustomUser> userFiltrationBuilder;
    private final @Qualifier("userFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateUserResponseDTO createUser(CustomUserDTO customUserDTO) {
        CustomUser customUser = customUserDTOMapper.mapDtoToUser(customUserDTO);
        customUser.setUserRoles(getRealUserRoleSet(customUserDTO.getUserRoles()));
        CustomUser realUser = customUserRepository.save(customUser);
        return new CreateUserResponseDTO(realUser.getId());
    }

    @Transactional
    public ResponseEntity<UpdateUserResponseDTO> updateUser(long id, UpdateUserRequestDTO updateUserRequestDTO) {
        Optional<CustomUser> optionalCustomUser;
        optionalCustomUser = customUserRepository.findById(id);
        if (optionalCustomUser.isPresent()) {
            CustomUser customUser = optionalCustomUser.get();
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
        } else {
            //todo throw exception
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<DeleteUserResponseDTO> deleteUser(List<Long> userIdList) {
        customUserRepository.deleteByIdIsIn(userIdList);
        return new ResponseEntity<>(HttpStatus.OK);
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
            List<String> userRoles,
            Pageable pageable) {
        Map<String, Object> filterValues = new HashMap<>();
        if (beforeBornDate != null) {
            filterValues.put("beforeBornDate", LocalDate.parse(beforeBornDate));
        }
        if (afterBornDate != null) {
            filterValues.put("afterBornDate", LocalDate.parse(afterBornDate));
        }
        filterValues.put("name", name);
        filterValues.put("surname", surname);
        filterValues.put("patronymic", patronymic);
        filterValues.put("town", town);
        filterValues.put("street", street);
        filterValues.put("house", house);
        filterValues.put("flat", flat);
        if (userRoles != null) {
            List<UserRole.UserRoleType> roleTypeList = new ArrayList<>();
            UserRole.UserRoleType typeContainer;
            for (String currentUserRole : userRoles) {
                typeContainer = UserRole.UserRoleType.valueOf(currentUserRole);
                roleTypeList.add(typeContainer);
            }
            filterValues.put("userRoles", roleTypeList);
        }
        Specification<CustomUser> specification = takeGetUsersSpecification(filterValues);
        return customUserRepository.findAll(specification, pageable);
    }

    public CustomUser getUser(long id) {
        Optional<CustomUser> optionalUser = customUserRepository.findById(id);
        if (optionalUser.isEmpty()) {
            //todo throw exception
        }
        return optionalUser.get();
    }

    private Specification<CustomUser> takeGetUsersSpecification(Map<String, Object> filterValues) {
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
        Set<UserRole> realUserRoles = getRealUserRoleSet((List<UserRole.UserRoleType>) filterValues.get("userRoles"));
        for (UserRole currentUserRole : realUserRoles) {
            userFiltrationBuilder.addCriteria(
                    currentUserRole != null,
                    "userRoles",
                    KeyWords.FILTER_OPERATION_EQUALS_SET_FIELD_ELEMENT,
                    currentUserRole);
        }
        return userFiltrationBuilder.build(fieldCriteriaTypes);
    }

    private Set<UserRole> getRealUserRoleSet(List<UserRole.UserRoleType> dtoUserRoles) {
        List<UserRole> realUserRoleList = userRoleRepository.findUserRolesByRoleTypeIsIn(dtoUserRoles);
        HashSet<UserRole> realRoleSet = null;
        if (realUserRoleList.size() > 0) {
            realRoleSet = new HashSet<>(realUserRoleList);
        } else {
            //todo throw exception
        }
        return realRoleSet;
    }

}
