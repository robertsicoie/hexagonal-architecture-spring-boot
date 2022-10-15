package consulting.convex.hexagonal.adapter.db.mapper;

import consulting.convex.hexagonal.adapter.db.entity.MovieEntity;
import consulting.convex.hexagonal.domain.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface DatabaseMapper {

  Movie toMovie(MovieEntity entity);

  MovieEntity toEntity(Movie movie);

}
