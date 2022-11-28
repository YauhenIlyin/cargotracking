package by.ilyin.core.repository;

import by.ilyin.core.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Set<UserRole> findByRoleTypeIsIn(Set<UserRole.UserRoleType> roleTypeSet);

}
