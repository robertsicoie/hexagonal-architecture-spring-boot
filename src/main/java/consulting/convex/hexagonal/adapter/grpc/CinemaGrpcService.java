package consulting.convex.hexagonal.adapter.grpc;

import consulting.convex.hexagonal.adapter.grpc.generated.CinemaServiceGrpc.CinemaServiceImplBase;
import consulting.convex.hexagonal.adapter.grpc.generated.MovieAddRequest;
import consulting.convex.hexagonal.adapter.grpc.generated.MovieResponse;
import consulting.convex.hexagonal.adapter.grpc.mapper.GrpcMapper;
import consulting.convex.hexagonal.domain.model.Movie;
import consulting.convex.hexagonal.domain.service.CinemaService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class CinemaGrpcService extends CinemaServiceImplBase {

  @Autowired
  private CinemaService cinemaService;

  @Autowired
  private GrpcMapper mapper;

  @Override
  public void add(MovieAddRequest request, StreamObserver<MovieResponse> responseObserver) {
    final Movie movie = cinemaService.add(mapper.toMovie(request));
    responseObserver.onNext(mapper.toGrpc(movie));
    responseObserver.onCompleted();
  }
}
