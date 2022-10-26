package consulting.convex.hexagonal.adapter.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import consulting.convex.hexagonal.adapter.db.entity.MovieEntity;
import consulting.convex.hexagonal.adapter.db.mapper.DatabaseMapper;
import consulting.convex.hexagonal.adapter.db.mapper.DatabaseMapperImpl;
import consulting.convex.hexagonal.domain.model.Movie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MovieRepositoryTest {

  @TestConfiguration
  static class MovieRepositoryConfiguration {

    static MovieEntity YEAR_2000_SNATCH = MovieEntity.builder().id(0)
        .title("Snatch")
        .description("Unscrupulous boxing promoters [...] fight to track down a priceless stolen diamond.")
        .year(2000)
        .build();

    static MovieEntity YEAR_1999_FIGHT_CLUB = MovieEntity.builder().id(1)
        .title("Fight Club")
        .description("An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.")
        .year(1999)
        .build();

    private List<MovieEntity> movieEntityList = new ArrayList<>(Arrays.asList(YEAR_2000_SNATCH, YEAR_1999_FIGHT_CLUB));

    @Primary
    @Bean
    public MovieJpaRepository testMovieJpaRepository() {
      return new MovieJpaRepository() {
        @Override
        public List<MovieEntity> findByYear(Integer year) {
          return movieEntityList.stream().filter(m -> year.equals(m.getYear())).collect(Collectors.toList());
        }

        @Override
        public List<MovieEntity> findAll() {
          return null;
        }

        @Override
        public List<MovieEntity> findAll(Sort sort) {
          return null;
        }

        @Override
        public List<MovieEntity> findAllById(Iterable<Long> longs) {
          return null;
        }

        @Override
        public <S extends MovieEntity> List<S> saveAll(Iterable<S> entities) {
          return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends MovieEntity> S saveAndFlush(S entity) {
          return null;
        }

        @Override
        public <S extends MovieEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
          return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<MovieEntity> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public MovieEntity getOne(Long aLong) {
          return null;
        }

        @Override
        public MovieEntity getById(Long aLong) {
          return null;
        }

        @Override
        public MovieEntity getReferenceById(Long aLong) {
          return null;
        }

        @Override
        public <S extends MovieEntity> List<S> findAll(Example<S> example) {
          return null;
        }

        @Override
        public <S extends MovieEntity> List<S> findAll(Example<S> example, Sort sort) {
          return null;
        }

        @Override
        public Page<MovieEntity> findAll(Pageable pageable) {
          return null;
        }

        @Override
        public <S extends MovieEntity> S save(S entity) {
          return null;
        }

        @Override
        public Optional<MovieEntity> findById(Long aLong) {
          return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
          return false;
        }

        @Override
        public long count() {
          return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(MovieEntity entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends MovieEntity> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends MovieEntity> Optional<S> findOne(Example<S> example) {
          return Optional.empty();
        }

        @Override
        public <S extends MovieEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
          return null;
        }

        @Override
        public <S extends MovieEntity> long count(Example<S> example) {
          return 0;
        }

        @Override
        public <S extends MovieEntity> boolean exists(Example<S> example) {
          return false;
        }

        @Override
        public <S extends MovieEntity, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
          return null;
        }
      };
    }

    @Bean
    public MovieRepository movieRepository() {
      return new MovieRepository();
    }

    @Bean DatabaseMapper databaseMapper() {
      return new DatabaseMapperImpl();
    }
  }

  @Autowired
  private MovieRepository movieRepository;

  @Test
  void shouldFindMovies() {
    final List<Movie> movies = movieRepository.getByYear(2000);
    assertNotNull(movies);
    assertEquals(1, movies.size());
    final Movie movie = movies.get(0);
    assertEquals(MovieRepositoryConfiguration.YEAR_2000_SNATCH.getTitle(), movie.getTitle());
    assertEquals(MovieRepositoryConfiguration.YEAR_2000_SNATCH.getDescription(), movie.getDescription());
  }
}
