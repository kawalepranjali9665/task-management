package com.app.taskservice.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.taskservice.user.UserDto;
import com.app.taskservice.user.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
    private TaskService taskService;
	
	@Autowired
	private UserService userService;

    @PostMapping
    public Task createTask(@RequestBody Task task, @RequestParam String email) throws Exception {
    	UserDto user = userService.getUserByEmail(email);
    	task.setUserId(user.getId());
        return taskService.createTask(task, user.getRole());
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }
    
    @GetMapping("/all-task") 
	public List<Task> getAllTaskByUser(@RequestParam String email) {
		UserDto user = userService.getUserByEmail(email);
		return taskService.getAllTaskByUser(user.getId());
	} 
    
	@GetMapping("/user/{userId}")
	public List<Task> getAssignedUsersTask(@PathVariable Integer userId,
			@RequestParam(required = false) TaskStatus status) {
		return taskService.assignedUsersTask(userId, status);
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Integer id, @RequestBody Task req, @RequestParam String email) {
		UserDto user = userService.getUserByEmail(email);
		return taskService.updateTask(id, req, user.getId());
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
	}
	
	@PutMapping("/{id}/complete")
	public Task completeTask(@PathVariable Integer id) {
		return taskService.completeTask(id);
	}
}
