package consulting.convex.hexagonal.adapter.db;

import consulting.convex.hexagonal.adapter.db.entity.MovieEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {

  List<MovieEntity> findByYear(Integer year);

}
