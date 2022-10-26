package consulting.convex.hexagonal.adapter.grpc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.adapter.grpc.generated.MovieAddRequest;
import org.junit.jupiter.api.Test;

public class CinemaGrpcServiceTest {

  @Test
  void shouldCreateAddMovieRequest() {
    final MovieAddRequest movieAddReq = MovieAddRequest.newBuilder()
        .setTitle("Movie Title")
        .setDescription("Movie Description")
        .setYear(1991).build();
    assertNotNull(movieAddReq);
  }

}
