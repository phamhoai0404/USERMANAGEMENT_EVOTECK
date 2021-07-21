package USERMANAGEMENT.repository.auth;


import USERMANAGEMENT.model.auth.ERole;
import USERMANAGEMENT.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
