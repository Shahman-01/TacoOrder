package tacos.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_at")
	private Date createdAt;

	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;

	@ManyToMany(targetEntity=Ingredient.class)
	@Size(min = 1, message = "You must choose at least one ingredient")
	private List<Ingredient> ingredients;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
