package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.models.User;

@Repository
public interface JpaUserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
