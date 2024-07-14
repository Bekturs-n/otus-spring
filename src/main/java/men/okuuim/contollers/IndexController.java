package men.okuuim.contollers;

import lombok.RequiredArgsConstructor;
import men.okuuim.dto.LoginResponseDto;
import men.okuuim.domain.Users;
import men.okuuim.dto.LoginUserDto;
import men.okuuim.security.service.AuthenticationService;
import men.okuuim.security.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @CrossOrigin разрешение проблемы Cross-Origin Request Blocked
 * @see <a href="https://habr.com/ru/companies/macloud/articles/553826/"> </a>/>
 *
 * решается на стороне фронта проксированием запросов
 */

//todo Это проверочный контроллер из-за ошибки Cross-Origin Request Blocked создал его что бы полностью проверить
// todo далее этот контроллер будет обраатывать стартовые запросы типа / или без него
//@CrossOrigin
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public String slash() {
        return "test";
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            Users authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponseDto response = LoginResponseDto.builder()
                    .id(authenticatedUser.getId())
                    .token(jwtToken)
                    .name(authenticatedUser.getFirstName())
                    .expiresIn(jwtService.getExpirationTime())
                    .build();

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
