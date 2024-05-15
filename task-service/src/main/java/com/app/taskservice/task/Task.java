package com.app.taskservice.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TASK")
@Getter
@Setter
public class Task {

	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "TITLE")
    private String title;
	
	@Column(name = "DESCRIPTION")
    private String description;
	
	@Column(name = "STATUS")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private TaskStatus status;

	@Column(name = "DEADLINE")
	private LocalDateTime deadline;
	
	@Column(name = "CREATE_AT")
	private LocalDateTime createAt; 
	
	@Column(name = "USER_ID")
    private Integer userId;
	
	@Transient
	private List<String> tags = new ArrayList<>(); 
}
