package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.dao.JpaUserRepository;
import tacos.models.User;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private JpaUserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(JpaUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (Objects.nonNull(user)) {
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}
}
