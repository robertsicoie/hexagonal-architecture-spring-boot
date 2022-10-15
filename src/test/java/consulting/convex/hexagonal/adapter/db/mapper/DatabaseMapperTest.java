package consulting.convex.hexagonal.adapter.db.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.adapter.TestData;
import consulting.convex.hexagonal.adapter.db.entity.MovieEntity;
import consulting.convex.hexagonal.domain.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabaseMapperTest {

  @Autowired
  DatabaseMapper databaseMapper;

  @Test
  void shouldMapToMovie() {
    final MovieEntity movieEntity = TestData.YEAR_1999_FIGHT_CLUB;

    final Movie movie = databaseMapper.toMovie(movieEntity);

    assertNotNull(movie);
    assertEquals(movieEntity.getId(), movie.getId());
    assertEquals(movieEntity.getTitle(), movie.getTitle());
    assertEquals(movieEntity.getDescription(), movie.getDescription());
    assertEquals(movieEntity.getYear(), movie.getYear());
  }

  @Test
  void shouldMapToMovieEntity() {
    final Movie movie = Movie.builder()
        .id(0L)
        .title("Forrest Gump")
        .description("The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.")
        .year(1994)
        .build();

    final MovieEntity movieEntity = databaseMapper.toEntity(movie);

    assertNotNull(movieEntity);
    assertEquals(movie.getId(), movieEntity.getId());
    assertEquals(movie.getTitle(), movieEntity.getTitle());
    assertEquals(movie.getDescription(), movieEntity.getDescription());
    assertEquals(movie.getYear(), movieEntity.getYear());
  }

}
