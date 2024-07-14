package men.okuuim.security.service;

import lombok.RequiredArgsConstructor;
import men.okuuim.domain.Users;
import men.okuuim.dto.LoginUserDto;
import men.okuuim.repository.UsersDao;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersDao userRepository;
    private final AuthenticationManager authenticationManager;

    public Users authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.getUserByEmail(input.getEmail())
                .orElseThrow();
    }
}
