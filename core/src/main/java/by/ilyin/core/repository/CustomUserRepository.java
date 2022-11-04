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

    @NonNull
    CustomUser save(@NonNull CustomUser customUser);

    @Override
    void delete(@NonNull CustomUser customUser);

    void deleteCustomUsersByIdIsIn(List<Long> idList);

    @NonNull
    @Override
    Page<CustomUser> findAll(@NonNull Pageable pageable);

    @NonNull
    @Override
    Page<CustomUser> findAll(Specification specification, @NonNull Pageable pageable);

    @NonNull
    @Override
    Optional<CustomUser> findById(@NonNull Long id);

}
