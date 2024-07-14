package men.okuuim.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import men.okuuim.domain.Users;
import men.okuuim.dto.UserDto;
import men.okuuim.repository.UsersDao;
import men.okuuim.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersDao userDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserBy(long id) {
        return userDao.findUserById(id).map(value -> modelMapper.map(value, UserDto.class)).orElse(null);
    }

    @Override
    public void update(UserDto studentDto) {
        Optional<Users> optional = userDao.findById(studentDto.getId());
        if (optional.isEmpty()) {
            log.error("No author with this credential");
            return;
        }
        studentDto.setEmail(optional.get().getEmail());
        modelMapper.map(studentDto, optional.get());
        userDao.save(optional.get());
    }

    @Override
    public Users save(UserDto studentDto) {
        return userDao.save(modelMapper.map(studentDto, Users.class));
    }

    //todo get object not id
    @Override
    public void remove(Long id) {
        userDao.findUserById(id).ifPresent(userDao::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userDao.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

}
