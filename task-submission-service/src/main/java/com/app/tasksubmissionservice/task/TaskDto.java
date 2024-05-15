package com.app.tasksubmissionservice.task;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class TaskDto {

	private Integer id;

	private String title;

	private String description;

	private TaskStatus status;

	private LocalDateTime deadline;

	private LocalDateTime createAt;
}
