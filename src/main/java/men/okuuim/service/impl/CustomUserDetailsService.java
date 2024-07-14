package men.okuuim.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import men.okuuim.domain.Users;
import men.okuuim.repository.UsersDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userDao.getUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Unknown user: " + email);
        }

        return optionalUser.get();
    }
}
