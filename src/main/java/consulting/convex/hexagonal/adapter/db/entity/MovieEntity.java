package consulting.convex.hexagonal.adapter.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String title;

  private String description;

  private Integer year;
}
