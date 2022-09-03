import com.project.movierama.dtos.LoginRequestDto
import com.project.movierama.services.AuthService
import com.project.movierama.services.AuthServiceImpl
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import spock.lang.Specification

class AuthenticationServiceSpec extends Specification {

    AuthenticationManager authenticationManager = Mock(AuthenticationManager)
    AuthService service = new AuthServiceImpl(authenticationManager)

    def "login an existing user"() {

        given: "a login request from the user"
        LoginRequestDto loginRequestDto = new LoginRequestDto()
        loginRequestDto.setUsername("tommy")
        loginRequestDto.setPassword("123")

        when: "the login service method is called"
        service.authenticate(loginRequestDto)

        then:
        1 * authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(), loginRequestDto.getPassword()))
    }

}
