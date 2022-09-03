import com.project.movierama.dtos.MovieRequestDto
import com.project.movierama.entities.Movie
import com.project.movierama.entities.User
import com.project.movierama.repositories.MovieRepository
import com.project.movierama.repositories.UserRepository
import com.project.movierama.services.MovieServiceImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import spock.lang.Specification

class MovieServiceSpec extends Specification {

    MovieRepository movieRepository = Mock(MovieRepository);
    UserRepository userRepository = Mock(UserRepository);

    MovieServiceImpl service = new MovieServiceImpl(movieRepository, userRepository)

    def "save a new movie in db"() {

        given: "a request for saving new movie"
        MovieRequestDto request = buildMovieRequestDto()

        User user = new User()
        user.setId(1L)

        Movie movie = new Movie();
        movie.setId(1L)

        when: "the service save method is called"
        service.save(request)

        then:
        1 * movieRepository.existsByTitleAndReleaseYear(request.getTitle(), request.getReleaseYear()) >> false
        1 * userRepository.findById(request.getUserId()) >> Optional.of(user)
        1 * movieRepository.save(_) >> movie
    }

    def "save a new movie which already exists in db"() {

        given: "a request for saving new movie"
        MovieRequestDto request = buildMovieRequestDto()


        when: "the service save method is called"
        service.save(request)

        then:
        1 * movieRepository.existsByTitleAndReleaseYear(request.getTitle(), request.getReleaseYear()) >> true
        Exception ex = thrown()
        ex.getMessage() == "Movie has already been added."
    }

    def "user does not exist when saving movie"() {

        given: "a request for saving new movie"
        MovieRequestDto request = buildMovieRequestDto()

        User user = new User()
        user.setId(1L)

        when: "the service save method is called"
        service.save(request)

        then:
        1 * movieRepository.existsByTitleAndReleaseYear(request.getTitle(), request.getReleaseYear()) >> false
        1 * userRepository.findById(request.getUserId()) >> Optional.empty()
        Exception ex = thrown()
        ex.getMessage() == "User with id 1 does not exist."
    }

    private static Pageable buildPageable() {
        Sort sort = Sort.by(Sort.Direction.fromString(Sort.Direction.DESC.name()), "title");
        Pageable pageable = PageRequest.of(0, 5, sort);
        pageable
    }

    private static MovieRequestDto buildMovieRequestDto() {
        MovieRequestDto request = new MovieRequestDto()
        request.setUserId(1L)
        request.setTitle("Pokemon")
        request.setPlot("Plot")
        request.setReleaseYear((short) 2010)
        request
    }
}
