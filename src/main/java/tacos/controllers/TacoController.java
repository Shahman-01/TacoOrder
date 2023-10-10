package tacos.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.dao.TacoRepository;
import tacos.models.Taco;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
	private TacoRepository tacoRepo;

	public TacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@GetMapping(params = "recent")
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(
				0, 12, Sort.by("createdAt").descending());
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> getTaco(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}

	@DeleteMapping("/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTaco(@PathVariable("orderId") Long orderId) {
		try {
			tacoRepo.deleteById(orderId);
		} catch (EmptyResultDataAccessException ignored) {}
	}
}
