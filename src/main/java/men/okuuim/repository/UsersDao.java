package men.okuuim.repository;

import java.util.List;
import java.util.Optional;
import men.okuuim.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersDao extends CrudRepository<Users, Long> {

    List<Users> findAll();

    Optional<Users> findUserById(long id);

    Optional<Users> getUserByEmail(String email);

//    User getUserByEmailOrId(String email, long id);

}
