package com.beautysalon.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautysalon.scheduling.model.ProfessionalUser;

public interface ProfessionalUserRepository extends JpaRepository<ProfessionalUser,Long>{
    
}
