package consulting.convex.hexagonal.adapter.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.adapter.rest.command.AddMovieCommand;
import consulting.convex.hexagonal.domain.model.Movie;
import org.junit.jupiter.api.Test;

class RestMapperTest {

  private final RestMapper restMapper = new RestMapperImpl();

  @Test
  void shouldMapToMovie() {
    final AddMovieCommand addCommand = AddMovieCommand.builder()
        .title("Inception")
        .description("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.")
        .year(2010)
        .build();

    final Movie movie = restMapper.toMovie(addCommand);

    assertNotNull(movie);
    assertEquals(addCommand.getTitle(), movie.getTitle());
    assertEquals(addCommand.getDescription(), movie.getDescription());
    assertEquals(addCommand.getYear(), movie.getYear());
  }
}
