package by.ilyin.core.repository;

import by.ilyin.core.entity.HappyBirthdayTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HappyBirthdayTemplateRepository extends JpaRepository<HappyBirthdayTemplate, Long> {

    Optional<HappyBirthdayTemplate> findByClient_Id(Long clientId);

}
