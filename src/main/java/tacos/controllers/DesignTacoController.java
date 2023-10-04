package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.dao.IngredientRepository;
import tacos.dao.TacoRepository;
import tacos.models.Ingredient;
import tacos.models.Ingredient.Type;
import tacos.models.Taco;
import tacos.models.TacoOrder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private TacoRepository designRepo;

	@ModelAttribute(name = "order")
	public TacoOrder order() {
		return new TacoOrder();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model) {

		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(ingredients::add);

		Type[] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList()));
		}
		model.addAttribute("design", new Taco());
		return "design";
	}

	@PostMapping
	public String processDesign( @ModelAttribute TacoOrder tacoOrder,
	                             @ModelAttribute(value = "taco") @Valid Taco taco,
	                             Errors errors) {
		if(errors.hasErrors()) {
			log.info("errors");
			return "redirect:/design";
		}
		taco.setCreatedAt(new Date());
		log.info("Processing design: " + taco);
		log.info("Taco info {} , {}, {}, {}", taco.getId(), taco.getCreatedAt(), taco.getName(), taco.getIngredients());
		Taco saved = designRepo.save(taco);
		tacoOrder.addTaco(saved);

		return "redirect:/orders/current";
	}
}