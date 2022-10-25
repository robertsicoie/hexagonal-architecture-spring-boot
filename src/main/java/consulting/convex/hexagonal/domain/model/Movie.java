package consulting.convex.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
  private Long id;
  private String title;
  private String description;
  private Integer year;
  private Integer rate;

  public Integer getRate() {
    return rate != null ? rate : 0;
  }
}
