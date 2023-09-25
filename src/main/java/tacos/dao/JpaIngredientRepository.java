package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.models.Ingredient;

@Repository
public interface JpaIngredientRepository extends CrudRepository<Ingredient, String> {
}
