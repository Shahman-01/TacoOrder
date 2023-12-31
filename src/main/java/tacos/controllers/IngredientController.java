package tacos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tacos.dao.IngredientRepository;
import tacos.models.Ingredient;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {
	private IngredientRepository repo;

	@Autowired
	public IngredientController(IngredientRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public Iterable<Ingredient> findAll() {
		return repo.findAll();
	}

	@PostMapping
	@PreAuthorize("#{hasRole('ADMIN')}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
		return repo.save(ingredient);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("#{hasRole('ADMIN')}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIngredient(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
}
