package consulting.convex.hexagonal.adapter.rest;

import consulting.convex.hexagonal.adapter.rest.command.AddMovieCommand;
import consulting.convex.hexagonal.adapter.rest.mapper.RestMapper;
import consulting.convex.hexagonal.domain.model.Movie;
import consulting.convex.hexagonal.domain.service.CinemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  private RestMapper restMapper;

  @Autowired
  private CinemaService cinemaService;

  @GetMapping
  public List<Movie> getAll() {
    return cinemaService.getAllMovies();
  }

  @GetMapping("/filter/year/{year}")
  public List<Movie> getByLastName(@PathVariable Integer year) {
    return cinemaService.getByYear(year);
  }

  @PostMapping
  public Movie addMovie(@RequestBody AddMovieCommand addCommand) {
    Movie movie = restMapper.toMovie(addCommand);
    return cinemaService.add(movie);
  }
}
