package com.app.taskservice.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "TASK-USER-SERVICE",url = "http://localhost:8091") 
public interface UserService {  
  
    @GetMapping("/users/email") 
    public UserDto getUserByEmail(@RequestParam("email") String email); 
} 
