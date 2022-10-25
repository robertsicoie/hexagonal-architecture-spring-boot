package consulting.convex.hexagonal.domain.port;

import consulting.convex.hexagonal.domain.model.Movie;
import java.util.List;

public interface MovieRepositoryPort {

  List<Movie> getByYear(Integer year);

  List<Movie> getAll();

  Movie save(Movie movie);

  Movie getById(Long movieId);

  void deleteById(Long movieId);
}
