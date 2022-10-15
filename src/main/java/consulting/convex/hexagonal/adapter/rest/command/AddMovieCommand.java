package consulting.convex.hexagonal.adapter.rest.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddMovieCommand {
  private String title;
  private String description;
  private Integer year;
}
