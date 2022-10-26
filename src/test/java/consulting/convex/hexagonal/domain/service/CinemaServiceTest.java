package consulting.convex.hexagonal.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.domain.model.Movie;
import consulting.convex.hexagonal.domain.port.MovieRepositoryPort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CinemaServiceTest {

  @TestConfiguration
  static class CinemaServiceConfiguration {

    @Bean
    public MovieRepositoryPort testMovieRepository() {
      return new MovieRepositoryPort() {
        private final List<Movie> moviesList = new ArrayList<>(Arrays.asList(
            Movie.builder()
                .id(1L)
                .title("Snatch")
                .description(
                    "Unscrupulous boxing promoters [...] fight to track down a priceless stolen diamond.")
                .year(2000)
                .build()
        ));

        @Override
        public List<Movie> getByYear(Integer year) {
          return moviesList.stream().filter(m -> m.getYear() == year).collect(Collectors.toList());
        }

        @Override
        public List<Movie> getAll() {
          return moviesList;
        }

        @Override
        public Movie save(Movie movie) {
          movie.setId(Long.valueOf(moviesList.size()));
          moviesList.add(movie);
          return movie;
        }

        @Override
        public Movie getById(Long movieId) {
          return moviesList.stream().filter(m -> m.getId() == movieId.intValue()).findFirst().get();
        }

        @Override
        public void deleteById(Long movieId) {
          moviesList.remove(getById(movieId));
        }
      };
    }

    @Bean
    public CinemaService cinemaService() {
      return new CinemaService();
    }
  }

  @Autowired
  private CinemaService cinemaService;

  @Test
  void shouldRate() {
    assertNotNull(cinemaService.getAllMovies());
    assertEquals(1, cinemaService.getAllMovies().size());

    Movie movie = cinemaService.getAllMovies().get(0);
    assertEquals(0, movie.getRate());
    assertEquals(10, cinemaService.rate(movie.getId(), 10).getRate());
    assertEquals(15, cinemaService.rate(movie.getId(), 5).getRate());
  }

}
