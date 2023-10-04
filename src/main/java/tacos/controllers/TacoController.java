package tacos.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
		return tacoRepo.findAll(page).getContent();
	}

	@GetMapping("/{id}")
	public Optional<Taco> getTaco(@PathVariable("id") Long id) {
		return tacoRepo.findById(id);
	}
}
