package consulting.convex.hexagonal.adapter.grpc.mapper;

import consulting.convex.hexagonal.adapter.grpc.generated.MovieAddRequest;
import consulting.convex.hexagonal.adapter.grpc.generated.MovieResponse;
import consulting.convex.hexagonal.domain.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface GrpcMapper {

  Movie toMovie(MovieAddRequest movieAddRequest);

  MovieResponse toGrpc(Movie movie);
}
