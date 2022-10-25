package consulting.convex.hexagonal.adapter.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.adapter.rest.command.AddMovieCommand;
import consulting.convex.hexagonal.domain.model.Movie;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MovieControllerTest {
  private static String MOVIES_ENDPOINT = "http://localhost:%d/movies";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private static AddMovieCommand puplFiction;

  private static AddMovieCommand joker;

  @BeforeAll
  static void beforeAll() {
    puplFiction = AddMovieCommand.builder()
        .title("Pulp Fiction")
        .description("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.")
        .year(1994)
        .build();

    joker = AddMovieCommand.builder()
        .title("Joker")
        .description("A mentally troubled stand-up comedian embarks on a downward spiral that leads to the creation of an iconic villain.")
        .year(2019)
        .build();
  }

  @Test
  @Order(1)
  void shouldAdd() {
    final ResponseEntity<Movie> response =
        restTemplate.postForEntity(String.format(MOVIES_ENDPOINT, port), puplFiction, Movie.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(puplFiction.getTitle(), response.getBody().getTitle());
    assertEquals(puplFiction.getDescription(), response.getBody().getDescription());
    assertNotNull(response.getBody().getId());

    restTemplate.postForEntity(String.format(MOVIES_ENDPOINT, port), joker, Movie.class);
  }

  @Test
  @Order(2)
  void shouldGetAll() {
    final var allMoviesResponse = restTemplate.getForEntity(
        String.format(MOVIES_ENDPOINT, port),
        Movie[].class);
    assertEquals(HttpStatus.OK, allMoviesResponse.getStatusCode());

    final Movie[] allMovies = allMoviesResponse.getBody();
    assertNotNull(allMovies);
    assertEquals(2, allMovies.length);
  }

  @Test
  @Order(3)
  void shouldFilter() {
    final var filteredMoviesResponse = restTemplate.getForEntity(
        String.format(MOVIES_ENDPOINT, port) + "/filter/year/1994",
        Movie[].class);
    assertEquals(HttpStatus.OK, filteredMoviesResponse.getStatusCode());

    final Movie[] filteredMovies = filteredMoviesResponse.getBody();
    assertEquals(1, filteredMovies.length);

    var movie = (Movie) filteredMovies[0];
    assertEquals(puplFiction.getTitle(), movie.getTitle());
    assertEquals(puplFiction.getDescription(), movie.getDescription());
  }

  @Test
  @Order(4)
  void shouldRemoveMovies() {
    final var allMoviesResponse = restTemplate.getForEntity(
        String.format(MOVIES_ENDPOINT, port),
        Movie[].class);

    final Movie[] allMovies = allMoviesResponse.getBody();
    assertNotNull(allMovies);
    Arrays.stream(allMovies).forEach(movie -> {
      assertEquals(HttpStatus.OK,
          restTemplate.exchange(
              String.format(MOVIES_ENDPOINT, port) + "/" + movie.getId(),
              HttpMethod.DELETE,
              HttpEntity.EMPTY,
              Void.class).getStatusCode());
    });

    assertEquals(0, restTemplate.getForEntity(
        String.format(MOVIES_ENDPOINT, port),
        Movie[].class).getBody().length);

  }
}
