package com.app.tasksubmissionservice.taskSubmission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tasksubmissionservice.task.TaskService;
import com.app.tasksubmissionservice.user.UserDto;
import com.app.tasksubmissionservice.user.UserService;

@RestController
@RequestMapping("/submissions")
public class TaskSubmissionController {

	@Autowired
	private TaskSubmissionService taskSubmissionService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public TaskSubmission getTaskSubmissionById(@PathVariable Integer id) {
		return taskSubmissionService.getSubmission(id);
	}

	@PostMapping
	public TaskSubmission submitTask(@RequestParam Integer taskId, @RequestParam String email,
			@RequestParam String submissionContent) {
		UserDto user = userService.getUserByEmail(email);
		return taskSubmissionService.submitTask(taskId, user.getId(), submissionContent);
	}

	@GetMapping
	public List<TaskSubmission> getAllTaskSubmissions() {
		return taskSubmissionService.getAllTaskSubmissions();
	}
	
	@GetMapping("/task/{taskId}")
	public List<TaskSubmission> getTaskSubmissionsByTaskId(@PathVariable Integer taskId) {
		return taskSubmissionService.getTaskSubmissionByTaskId(taskId);
	}
	
	@PutMapping("/{id}")
	public TaskSubmission acceptOrDeclineTaskSubmission(
			@PathVariable Integer id,
			@RequestParam("status") String status) throws Exception {
		TaskSubmission submission = taskSubmissionService.acceptDeclineSubmission(id, status);

		if (submission.getStatus().equals("COMPLETE")) {
			taskService.completeTask(submission.getTaskId());
		}
		return submission;
	}

}
