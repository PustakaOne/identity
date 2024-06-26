package id.ac.ui.cs.pustakaone.identity.repository;

import id.ac.ui.cs.pustakaone.identity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsUserByEmail(String email);
}
