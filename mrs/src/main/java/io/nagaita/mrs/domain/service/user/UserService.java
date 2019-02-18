package io.nagaita.mrs.domain.service.user;

import io.nagaita.mrs.domain.model.User;
import io.nagaita.mrs.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> listAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}
}
