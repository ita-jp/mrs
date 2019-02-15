package io.nagaita.mrs.domain.service.user;

import io.nagaita.mrs.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//@formatter:off
		return userRepository.findById(username)
				.map(ReservatioinDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException(username + " is not found"));
		//@formatter:on
	}
}
