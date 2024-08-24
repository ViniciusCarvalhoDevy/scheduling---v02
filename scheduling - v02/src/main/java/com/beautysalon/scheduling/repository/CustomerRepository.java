package com.beautysalon.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.beautysalon.scheduling.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
