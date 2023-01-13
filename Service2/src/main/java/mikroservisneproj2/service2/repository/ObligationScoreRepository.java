package mikroservisneproj2.service2.repository;

import mikroservisneproj2.service2.domain.ObligationScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligationScoreRepository extends JpaRepository<ObligationScore, Long> {
}
