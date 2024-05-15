package com.app.tasksubmissionservice.user;

import lombok.Data;

@Data
public class UserDto {
	
	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String mobile;

	private String password;

	private String role;
}
