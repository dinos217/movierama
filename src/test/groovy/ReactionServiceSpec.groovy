import com.project.movierama.dtos.ReactionRequestDto
import com.project.movierama.entities.Movie
import com.project.movierama.entities.Reaction
import com.project.movierama.entities.User
import com.project.movierama.repositories.MovieRepository
import com.project.movierama.repositories.ReactionRepository
import com.project.movierama.repositories.UserRepository
import com.project.movierama.services.ReactionServiceImpl
import spock.lang.Specification

class ReactionServiceSpec extends Specification {

    UserRepository userRepository = Mock(UserRepository)
    MovieRepository movieRepository = Mock(MovieRepository)
    ReactionRepository reactionRepository = Mock(ReactionRepository)

    ReactionServiceImpl service = new ReactionServiceImpl(userRepository, movieRepository, reactionRepository)

    def "save a new reaction in db"() {

        given: "a new reaction request"
        ReactionRequestDto requestDto = new ReactionRequestDto()
        requestDto.setUserId(1L)
        requestDto.setMovieId(1L)
        requestDto.setLike(true)

        User user = new User()
        user.setId(1L)

        User user2 = new User()
        user.setId(2L)

        Movie movie = new Movie();
        movie.setId(1L)
        movie.setUser(user2)

        Reaction reaction = new Reaction()
        reaction.setLike(true)
        reaction.setUser(user)
        reaction.setMovie(movie)

        when: "the save service method is called"
        service.save(requestDto)

        then:
        1 * userRepository.findById(requestDto.getUserId()) >> Optional.of(user)
        1 * movieRepository.findById(requestDto.getMovieId()) >> Optional.of(movie)
        1 * reactionRepository.existsByUserAndMovie(user, movie) >> false
        1 * reactionRepository.save(_) >> reaction
    }

    def "save a new user reaction for a movie that is added by the same user"() {

        given: "a new reaction request"
        ReactionRequestDto requestDto = new ReactionRequestDto()
        requestDto.setUserId(1L)
        requestDto.setMovieId(1L)
        requestDto.setLike(true)

        User user = new User()
        user.setId(1L)

        Movie movie = new Movie();
        movie.setId(1L)
        movie.setUser(user)

        when: "the save service method is called"
        service.save(requestDto)

        then:
        1 * userRepository.findById(requestDto.getUserId()) >> Optional.of(user)
        1 * movieRepository.findById(requestDto.getMovieId()) >> Optional.of(movie)
        Exception ex = thrown()
        ex.getMessage() == "Users cannot vote for movies they have added."
    }

    def "save a new reaction but user has already voted"() {

        given: "a new reaction request"
        ReactionRequestDto requestDto = new ReactionRequestDto()
        requestDto.setUserId(1L)
        requestDto.setMovieId(1L)
        requestDto.setLike(true)

        User user = new User()
        user.setId(1L)

        User user2 = new User()
        user.setId(2L)
        user.setUsername("tommy")

        Movie movie = new Movie();
        movie.setId(1L)
        movie.setUser(user2)

        when: "the save service method is called"
        service.save(requestDto)

        then:
        1 * userRepository.findById(requestDto.getUserId()) >> Optional.of(user)
        1 * movieRepository.findById(requestDto.getMovieId()) >> Optional.of(movie)
        1 * reactionRepository.existsByUserAndMovie(user, movie) >> true
        Exception ex = thrown()
        ex.getMessage() == "User " + user.getUsername() + " has already voted for this movie."
    }

    def "update a user's vote"() {

        given: "a new reaction request"
        ReactionRequestDto requestDto = new ReactionRequestDto()
        requestDto.setUserId(1L)
        requestDto.setMovieId(1L)
        requestDto.setLike(true)

        User user = new User()
        user.setId(1L)

        User user2 = new User()
        user.setId(2L)

        Movie movie = new Movie();
        movie.setId(1L)
        movie.setUser(user2)

        Reaction reaction = new Reaction()
        reaction.setLike(true)
        reaction.setUser(user)
        reaction.setMovie(movie)

        when: "the save service method is called"
        service.save(requestDto)

        then:
        1 * userRepository.findById(requestDto.getUserId()) >> Optional.of(user)
        1 * movieRepository.findById(requestDto.getMovieId()) >> Optional.of(movie)
        1 * reactionRepository.existsByUserAndMovie(user, movie) >> false
        1 * reactionRepository.save(_) >> reaction
    }

}
