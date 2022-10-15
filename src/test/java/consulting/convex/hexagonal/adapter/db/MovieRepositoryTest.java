package consulting.convex.hexagonal.adapter.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.adapter.TestData;
import consulting.convex.hexagonal.domain.model.Movie;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MovieRepositoryTest {

  @MockBean
  private MovieJpaRepository mockJpaRepository;

  @Autowired
  private MovieRepository movieRepository;

  @BeforeEach
  void setUp() {
    Mockito
        .when(mockJpaRepository.findByYear(2010))
        .thenReturn(List.of(TestData.YEAR_2010_SNATCH));
  }

  @Test
  void shouldFindMovies() {
    final List<Movie> movies = movieRepository.getByYear(2010);
    assertNotNull(movies);
    assertEquals(1, movies.size());
    final Movie movie = movies.get(0);
    assertEquals(TestData.YEAR_2010_SNATCH.getTitle(), movie.getTitle());
    assertEquals(TestData.YEAR_2010_SNATCH.getDescription(), movie.getDescription());
  }
}
