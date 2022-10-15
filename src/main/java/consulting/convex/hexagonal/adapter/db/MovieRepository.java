package consulting.convex.hexagonal.adapter.db;

import consulting.convex.hexagonal.adapter.db.mapper.DatabaseMapper;
import consulting.convex.hexagonal.domain.model.Movie;
import consulting.convex.hexagonal.domain.port.MovieRepositoryPort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository implements MovieRepositoryPort {

  @Autowired
  private MovieJpaRepository repository;

  @Autowired
  private DatabaseMapper databaseMapper;

  @Override
  public List<Movie> getByYear(Integer year) {
    return repository.findByYear(year)
        .stream()
        .map(databaseMapper::toMovie)
        .collect(Collectors.toList());
  }

  @Override
  public List<Movie> getAll() {
    return repository.findAll()
        .stream()
        .map(databaseMapper::toMovie)
        .collect(Collectors.toList());
  }

  @Override
  public Movie save(Movie movie) {
    return databaseMapper.toMovie(repository.save(databaseMapper.toEntity(movie)));
  }
}
