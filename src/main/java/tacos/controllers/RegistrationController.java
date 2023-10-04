package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.dao.JpaUserRepository;
import tacos.security.RegistrationForm;

@Controller
@Slf4j
@RequestMapping("/register")
public class RegistrationController {
	private JpaUserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public RegistrationController(JpaUserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String registerForm() {
		return "registration";
	}

	@PostMapping
	public String processRegistration(RegistrationForm form) {
		userRepository.save(form.toUser(passwordEncoder));
		log.info("Registered");
		return "redirect:/login";
	}
}
