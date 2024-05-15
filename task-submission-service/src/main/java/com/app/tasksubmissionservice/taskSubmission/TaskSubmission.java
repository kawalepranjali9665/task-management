package com.app.tasksubmissionservice.taskSubmission;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TASK_SUBMISSION")
@Getter
@Setter
public class TaskSubmission {
	
	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "TASK_ID")
    private Integer taskId;
	
	@Column(name = "USER_ID")
    private Integer userId;
	
	@Column(name = "SUBMISSION_CONTENT")
    private String submissionContent;
	
	@Column(name = "STATUS")
	private String status = "PENDING"; 
    
	@Column(name = "SUBMISSION_TIME")
    private LocalDateTime submissionTime; 
	
}
