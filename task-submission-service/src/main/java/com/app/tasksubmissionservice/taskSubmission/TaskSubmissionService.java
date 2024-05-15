package com.app.tasksubmissionservice.taskSubmission;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskSubmissionService {

	@Autowired
	private TaskSubmissionRepository taskSubmissionRepository;

	public TaskSubmission getSubmission(Integer id) {
		return taskSubmissionRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public TaskSubmission submitTask(Integer taskId, Integer userId, String submissionContent) {
		TaskSubmission taskSubmission = new TaskSubmission();
		taskSubmission.setTaskId(taskId);
		taskSubmission.setUserId(userId);
		taskSubmission.setStatus("SUBMITTED");
		taskSubmission.setSubmissionContent(submissionContent);
		taskSubmission.setSubmissionTime(LocalDateTime.now());
		return taskSubmissionRepository.save(taskSubmission);
	}
	
	public TaskSubmission getTaskSubmissionById(Integer submissionId) throws Exception {
        return taskSubmissionRepository.findById(submissionId)
                .orElseThrow(() -> new Exception("Task submission not found"));
    }

    public List<TaskSubmission> getAllTaskSubmissions() {
        return taskSubmissionRepository.findAll();
    }

    public List<TaskSubmission> getTaskSubmissionByTaskId(Integer taskId) {
        return taskSubmissionRepository.findByTaskId(taskId);
    }
    
	public TaskSubmission acceptDeclineSubmission(Integer id, String status) throws Exception {
		TaskSubmission taskSubmission = taskSubmissionRepository.findById(id)
				.orElseThrow(() -> new Exception("Task submission not found"));
		taskSubmission.setStatus(status);
		return taskSubmissionRepository.save(taskSubmission);
	}
}
