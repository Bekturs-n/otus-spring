package men.okuuim.service.impl;

import java.util.List;
import java.util.Optional;
import men.okuuim.domain.Users;
import men.okuuim.dto.UserDto;
import men.okuuim.repository.UsersDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UsersDao userDao;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private UserServiceImpl service;

    private Users user;

    private static final Long ID = 1L;

    @BeforeEach
    public void beforeAll() {
        user = Users.builder()
                .id(ID)
                .firstName("Name")
                .lastName("Surname")
                .email("Email")
                .course(List.of())
                .enabled(true)
                .build();
    }

    @Test
    void getUserBy() {
        when(userDao.findUserById(anyLong())).thenReturn(Optional.of(user));

        UserDto userDto = service.getUserBy(ID);

        assertThat(userDto).isNotNull();
        assertThat(userDto.getId()).isEqualTo(ID);
        assertThat(userDto.getLastName()).isEqualTo(user.getLastName());
        verify(userDao).findUserById(anyLong());
    }

    @Test
    @Disabled("Not ready")
    void update() {
        UserDto userDto = UserDto.builder().id(ID).build();

        when(userDao.findUserById(anyLong())).thenReturn(Optional.of(user));
        when(userDao.save(any())).thenReturn(user);

        service.update(userDto);

        verify(userDao).findUserById(anyLong());
        verify(userDao).save(any());
    }

    @Test
    void save() {
        UserDto userDto = UserDto.builder().id(ID).build();

        when(userDao.save(any())).thenReturn(user);

        service.save(userDto);

        verify(userDao).save(any());
    }

    @Test
    void remove() {
        when(userDao.findUserById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(userDao).delete(user);

        service.remove(ID);

        verify(userDao).findUserById(anyLong());
        verify(userDao).delete(any());
    }

    @Test
    void getAll() {
        when(userDao.findAll()).thenReturn(List.of(user));

        List<UserDto> list = service.getAll();

        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getLastName()).isEqualTo(user.getLastName());
    }
}