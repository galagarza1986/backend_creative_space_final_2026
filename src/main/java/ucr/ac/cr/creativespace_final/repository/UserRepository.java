package ucr.ac.cr.creativespace_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.creativespace_final.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
