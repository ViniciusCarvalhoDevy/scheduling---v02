package com.beautysalon.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautysalon.scheduling.model.TypeTask;

public interface TypeTaskRepository extends JpaRepository<TypeTask,Long> {
    
}
