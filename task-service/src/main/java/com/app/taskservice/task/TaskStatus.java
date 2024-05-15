package com.app.taskservice.task;

public enum TaskStatus {
	PENDING("PENDING"), ASSIGNED("ASSIGNED"), DONE("DONE");

	private final String name;

	TaskStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
