package com.app.tasksubmissionservice.taskSubmission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskSubmissionRepository extends JpaRepository<TaskSubmission, Integer>{

	List<TaskSubmission> findByTaskId(Integer taskId);

}
