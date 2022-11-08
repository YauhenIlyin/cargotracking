package by.ilyin.core.repository;

import by.ilyin.core.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findUserRolesByRoleTypeIsIn(List<UserRole.UserRoleType> list);

}
