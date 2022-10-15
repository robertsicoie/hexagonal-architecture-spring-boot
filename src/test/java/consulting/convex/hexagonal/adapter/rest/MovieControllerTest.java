package consulting.convex.hexagonal.adapter.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.adapter.rest.command.AddMovieCommand;
import consulting.convex.hexagonal.domain.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MovieControllerTest {
  private static String MOVIES_ENDPOINT = "http://localhost:%d/movies";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String moviesUrl;

  @Test
  void apiTest() {
    moviesUrl = String.format(MOVIES_ENDPOINT, port);

    final AddMovieCommand puplFiction = AddMovieCommand.builder()
        .title("Pulp Fiction")
        .description("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.")
        .year(1994)
        .build();

    final ResponseEntity<Movie> response = restTemplate
        .postForEntity(moviesUrl, puplFiction, Movie.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(puplFiction.getTitle(), response.getBody().getTitle());
    assertNotNull(response.getBody().getId());

    final AddMovieCommand joker = AddMovieCommand.builder()
        .title("Joker")
        .description("A mentally troubled stand-up comedian embarks on a downward spiral that leads to the creation of an iconic villain.")
        .year(2019)
        .build();

    restTemplate.postForEntity(moviesUrl, joker, Movie.class);

    final var allMoviesResponse = restTemplate.getForEntity(
        moviesUrl,
        Movie[].class);
    assertEquals(HttpStatus.OK, allMoviesResponse.getStatusCode());
    final Movie[] allMovies = allMoviesResponse.getBody();
    assertNotNull(allMovies);
    assertEquals(2, allMovies.length);

    final var filteredMoviesResponse = restTemplate.getForEntity(
        moviesUrl + "/filter/year/1994",
        Movie[].class);

    assertEquals(HttpStatus.OK, filteredMoviesResponse.getStatusCode());
    final Movie[] filteredMovies = filteredMoviesResponse.getBody();
    assertEquals(1, filteredMovies.length);
    var movie = (Movie) filteredMovies[0];
    assertEquals(puplFiction.getTitle(), movie.getTitle());
    assertEquals(puplFiction.getDescription(), movie.getDescription());
  }
}
