package men.okuuim.security.service;

import lombok.RequiredArgsConstructor;
import men.okuuim.domain.Student;
import men.okuuim.repository.StudentDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentDao studentDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentDao.getStudentByEmail(email);
        if (student == null) {
            throw new UsernameNotFoundException("Unknown user: " + email);
        }

        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .roles(student.getRole())
                .build();
    }
}
