package consulting.convex.hexagonal.adapter.rest.mapper;

import consulting.convex.hexagonal.adapter.rest.command.AddMovieCommand;
import consulting.convex.hexagonal.domain.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RestMapper {

  Movie toMovie(AddMovieCommand addCommand);

}
