package USERMANAGEMENT.repository;

import USERMANAGEMENT.model.User;
import USERMANAGEMENT.repository.customer.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    List<User> findByFullName(String fullName);

    List<User> findByUserDescribe(String describe);
    List<User> findAll();
    List<User> findByAddress(String address);

}

