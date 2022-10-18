package by.ilyin.core.repository;

import by.ilyin.core.entity.CustomUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long>, JpaSpecificationExecutor<CustomUser> {

    CustomUser save(CustomUser customUser);

    void delete(CustomUser customUser);

    void deleteCustomUsersByIdIsIn(List<Long> idList);

    Page<CustomUser> findAll(Pageable pageable);

    Page<CustomUser> findAll(Specification specification, Pageable pageable);

    Optional<CustomUser> findCustomUserById(long id);

    Optional<CustomUser> findCustomUserByLogin(String login);

}