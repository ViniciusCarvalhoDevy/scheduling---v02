package com.beautysalon.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.beautysalon.scheduling.model.Constumer;

@Repository
public interface CostumerRepository extends JpaRepository<Constumer,Long> {

}
