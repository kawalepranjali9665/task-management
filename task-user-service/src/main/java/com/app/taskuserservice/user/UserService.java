package com.app.taskuserservice.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
