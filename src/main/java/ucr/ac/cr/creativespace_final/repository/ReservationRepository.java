package ucr.ac.cr.creativespace_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.creativespace_final.model.ReservationEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    List<ReservationEntity> findByUserEmail(String email);
    List<ReservationEntity> findBySpaceId(Integer spaceId);
    List<ReservationEntity> findByDateReserved(LocalDate dateReserved);
    boolean existsBySpaceIdAndDateReserved(Integer spaceId, LocalDate dateReserved);
}
