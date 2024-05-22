package by.ilyin.core.repository;

import by.ilyin.core.entity.uuid.CustomUUID;
import by.ilyin.core.entity.uuid.UUIDCustomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CustomUUIDRepository extends JpaRepository<CustomUUID, UUIDCustomId>, JpaSpecificationExecutor<CustomUUID> {

    Optional<CustomUUID> findByUuidValueAndExpiredDateAfter(String uuid, LocalDateTime expiredDate);

    void deleteByUserIdAndDestination(Long id, CustomUUID.Destination destination);

}
