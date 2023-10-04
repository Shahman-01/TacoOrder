package tacos.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import tacos.models.Taco;

import java.net.ContentHandler;

public interface TacoRepository extends CrudRepository<Taco, Long> {
	ContentHandler findAll(PageRequest page);
}
