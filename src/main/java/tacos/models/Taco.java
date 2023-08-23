package tacos.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
	Long id;
	Date createdAt = new Date();

	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;

	@NotNull
	private List<Ingredient> ingredients;
}
