package com.beautysalon.scheduling.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautysalon.scheduling.model.Constumer;
import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.model.Scheduling;
import com.beautysalon.scheduling.model.TypeTask;

public interface SchedulingRepository extends JpaRepository<Scheduling,Long>{
    public Optional<Scheduling> findByIdClient(Constumer idClient);
    public Optional<Scheduling> findByIdTypeTask(TypeTask idTypeTask);
    public Optional<Scheduling> findByIdUserProfissional(ProfessionalUser idUserProfissional);
    public List<Scheduling> findAllByIdClient(List<Long> ids);
    public List<Scheduling> findAllByIdTypeTask(List<Long> ids);
    public List<Scheduling> findAllByIdUserProfissional(List<Long> ids);
}
