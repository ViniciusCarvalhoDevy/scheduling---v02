package com.beautysalon.scheduling.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautysalon.scheduling.model.Customer;
import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.model.Scheduling;
import com.beautysalon.scheduling.model.TaskType;

public interface SchedulingRepository extends JpaRepository<Scheduling,Long>{
    public Optional<Scheduling> findByIdClient(Customer idClient);
    public Optional<Scheduling> findByIdTypeTask(TaskType idTypeTask);
    public Optional<Scheduling> findByIdUserProfissional(ProfessionalUser idUserProfissional);
    public List<Scheduling> findAllByIdClient(List<Long> ids);
    public List<Scheduling> findAllByIdTypeTask(List<Long> ids);
    public List<Scheduling> findAllByIdUserProfissional(List<Long> ids);
}
