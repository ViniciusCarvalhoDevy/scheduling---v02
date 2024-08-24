package com.beautysalon.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautysalon.scheduling.model.Customer;
import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.model.Scheduling;
import com.beautysalon.scheduling.model.TaskType;

import java.time.LocalDate;
import java.time.LocalTime;


public interface SchedulingRepository extends JpaRepository<Scheduling,Long>{
    List<Scheduling> findByIdTypeTask(TaskType idTypeTask);
    List<Scheduling> findByIdClient(Customer idClient);
    List<Scheduling> findByIdUserProfissional(ProfessionalUser idUserProfissional);
    List<Scheduling> findByDateAgend(LocalDate dateAgend);
    List<Scheduling> findByHorsTime(LocalTime horsTime);
    List<Scheduling> findByDateAgendAndHorsTime(LocalDate dateAgend,LocalTime horsTime);
    

}
