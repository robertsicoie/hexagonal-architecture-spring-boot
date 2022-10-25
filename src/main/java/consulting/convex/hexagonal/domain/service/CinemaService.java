package consulting.convex.hexagonal.domain.service;

import consulting.convex.hexagonal.domain.model.Movie;
import consulting.convex.hexagonal.domain.port.MovieRepositoryPort;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

  @Autowired
  private MovieRepositoryPort movieRepository;

  public List<Movie> getAllMovies() {
    return movieRepository.getAll();
  }

  public Movie add(Movie movie) {
    return movieRepository.save(movie);
  }

  public List<Movie> getByYear(Integer year) {
    return movieRepository.getByYear(year);
  }

  public Movie rate(Long movieId, Integer rating) {
    final Movie movie = movieRepository.getById(movieId);
    movie.setRate(movie.getRate() + rating);
    return movieRepository.save(movie);
  }

  public void removeMovie(Long movieId) {
    movieRepository.deleteById(movieId);
  }
}
