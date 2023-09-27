package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.dao.JpaUserRepository;
import tacos.dao.OrderRepository;
import tacos.models.TacoOrder;
import tacos.models.User;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {

	private final OrderRepository orderRepository;
	private final JpaUserRepository userRepository;
	private int pageSize = 20;

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public OrderController(OrderRepository orderRepository, JpaUserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	@GetMapping
	public String ordersForUser(
			Model model) {
		model.addAttribute("orders",
				orderRepository.findAll());
		return "orderList";
	}

	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors,
	                           SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if (errors.hasErrors())
			return "orderForm";

		order.setUser(user);

		orderRepository.save(order);
		sessionStatus.setComplete();


		return "redirect:/";
	}
}
