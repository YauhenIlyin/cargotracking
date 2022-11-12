package by.ilyin.core.repository;

import by.ilyin.core.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long>, JpaSpecificationExecutor<CustomUser> {

    void deleteByIdIsIn(List<Long> idList);

}
