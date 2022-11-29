package by.ilyin.core.repository;

import by.ilyin.core.entity.CustomJWT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthRepository extends JpaRepository<CustomJWT, Long>, JpaSpecificationExecutor<CustomJWT> {

    void deleteAllByExpiredDateBefore(LocalDateTime localDateTime);

    List<CustomJWT> findAllByUserId(Long userId);

    List<CustomJWT> findAllByIsNotBlocked(Boolean isNotBlocked);

}
