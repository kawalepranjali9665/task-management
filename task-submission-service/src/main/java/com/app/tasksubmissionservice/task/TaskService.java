package com.app.tasksubmissionservice.task;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "TASK-SERVICE",url = "http://localhost:8092") 
public interface TaskService { 
  
  
    @GetMapping("/tasks/{id}") 
    TaskDto getTaskById(@PathVariable Integer id); 
  
  
    @PutMapping("/tasks/{id}/complete") 
    TaskDto completeTask(@PathVariable Integer id); 
  
} 
