package com.beautysalon.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautysalon.scheduling.model.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType,Long> {
    
}
