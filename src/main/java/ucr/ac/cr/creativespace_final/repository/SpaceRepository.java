package ucr.ac.cr.creativespace_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.creativespace_final.model.SpaceEntity;

import java.util.List;

public interface SpaceRepository extends JpaRepository<SpaceEntity, Integer> {
    List<SpaceEntity> findByType(String type);
    List<SpaceEntity> findByLocation(String location);
}
