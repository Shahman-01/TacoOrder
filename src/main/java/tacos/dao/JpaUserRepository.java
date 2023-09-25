package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import tacos.models.User;

public interface JpaUserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
