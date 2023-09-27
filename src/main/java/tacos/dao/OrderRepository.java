package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import tacos.models.TacoOrder;
import tacos.models.User;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
	List<TacoOrder> findByDeliveryZip(String deliveryZip);
	List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
			String deliveryZip, Date startDate, Date endDate);

}
