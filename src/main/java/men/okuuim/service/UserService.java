package men.okuuim.service;

import java.util.List;
import men.okuuim.domain.Users;
import men.okuuim.dto.UserDto;

public interface UserService {

    UserDto getUserBy(long id);

    void update(UserDto author);

    Users save(UserDto userDto);

    void remove(Long userId);

    List<UserDto> getAll();
}
