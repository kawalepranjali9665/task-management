package com.app.taskservice.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskService {

	@Autowired
    private TaskRepository taskRepository;

	@Transactional
	public Task createTask(Task task, String requestRole) throws Exception {
		if (!requestRole.equals("ROLE_ADMIN")) { 
            throw new Exception("Only admin can create tasks"); 
        } 
		task.setStatus(TaskStatus.PENDING); 
        task.setCreateAt(LocalDateTime.now()); 
		return taskRepository.save(task);
	}
	
	public List<Task> getAllTasks(TaskStatus taskStatus) {
		List<Task> allTasks = taskRepository.findAll();

		List<Task> fliteredTasks = allTasks.stream()
				.filter(task -> taskStatus == null || task.getStatus().name().equalsIgnoreCase(taskStatus.toString()))
				.collect(Collectors.toList());
		return fliteredTasks;
	}
	
	public Task updateTask(Integer id, Task updatedTask, Integer userId){
		Task existingTasks = getTaskById(id);
		if (updatedTask.getTitle() != null) {
			existingTasks.setTitle(updatedTask.getTitle());
		}
		if (updatedTask.getDescription() != null) {
			existingTasks.setDescription(updatedTask.getDescription());
		}
		if (updatedTask.getStatus() != null) {
			existingTasks.setStatus(updatedTask.getStatus());
		}

		if (updatedTask.getDeadline() != null) {
			existingTasks.setDeadline(updatedTask.getDeadline());
		}
		return taskRepository.save(existingTasks);
	} 

	public Task getTaskById(Integer id) {
		return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public void deleteTask(Integer id) { 
        taskRepository.deleteById(id); 
    }
	
	public List<Task> assignedUsersTask(Integer userId, TaskStatus taskStatus) { 
        List<Task> allTasks = taskRepository.findByUserId(userId); 
        return allTasks.stream() 
                .filter(task -> taskStatus == null || task.getStatus() == taskStatus) 
                .collect(Collectors.toList()); 
    } 
  
    public Task completeTask(Integer taskId) { 
        Task task = getTaskById(taskId); 
        task.setStatus(TaskStatus.DONE); 
        return taskRepository.save(task); 
    }

	public List<Task> getAllTaskByUser(Integer userId) {
		return taskRepository.findByUserId(userId);
	} 
}
