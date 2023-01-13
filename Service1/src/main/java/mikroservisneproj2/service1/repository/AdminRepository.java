package mikroservisneproj2.service1.repository;

import mikroservisneproj2.service1.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByUserInfoEmailAndUserInfoPassword(String email, String password);

    Optional<Admin> findAdminByUserInfoEmail(String email)
;
    boolean existsAdminByUserInfoEmail(String email);

    boolean existsAdminByUserInfoUsername(String username);
}
