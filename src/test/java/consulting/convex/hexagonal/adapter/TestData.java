package consulting.convex.hexagonal.adapter;

import consulting.convex.hexagonal.adapter.db.entity.MovieEntity;
import java.util.List;

public class TestData {

  public static MovieEntity YEAR_2010_SNATCH = MovieEntity.builder().id(0)
          .title("Snatch")
          .description("Unscrupulous boxing promoters [...] fight to track down a priceless stolen diamond.")
          .year(2000)
          .build();

  public static MovieEntity YEAR_1999_FIGHT_CLUB = MovieEntity.builder().id(1)
      .title("Fight Club")
      .description("An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.")
      .year(1999)
      .build();

  public static List<MovieEntity> movies = List.of(
      YEAR_1999_FIGHT_CLUB,
      YEAR_2010_SNATCH);

}
