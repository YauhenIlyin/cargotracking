package by.ilyin.core.repository;

import by.ilyin.core.entity.CustomUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long>, JpaSpecificationExecutor<CustomUser> {

    void deleteByIdIsIn(List<Long> idList);

    @Override
    Page<CustomUser> findAll(@NonNull Pageable pageable);

    @Override
    Page<CustomUser> findAll(Specification specification, @NonNull Pageable pageable);

    @Override
    Optional<CustomUser> findById(@NonNull Long id);

}
