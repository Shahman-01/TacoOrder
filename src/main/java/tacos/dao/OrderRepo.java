package tacos.dao;

import tacos.models.TacoOrder;

public interface OrderRepo {
	TacoOrder save(TacoOrder order);
}
