package com.app.taskuserservice.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("email")
	public User getUserByEmail(@RequestParam("email") String email) {
		return userService.getUserByEmail(email);
	}
}
