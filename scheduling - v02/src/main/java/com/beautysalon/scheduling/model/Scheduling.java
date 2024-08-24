package com.beautysalon.scheduling.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Scheduling")
@Table(name = "Scheduling")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany
    @JoinTable(
        name = "scheduling_professional",
        joinColumns = @JoinColumn(name = "scheduling_id"),
        inverseJoinColumns = @JoinColumn(name = "ProfessionalUser_id")
        )
    @EqualsAndHashCode.Include
    private List<ProfessionalUser> idUserProfissional;
    
    @ManyToMany
    @JoinTable(
        name = "scheduling_customer",
        joinColumns = @JoinColumn(name = "scheduling_id"),
        inverseJoinColumns = @JoinColumn(name = "Customer_id")
        )
    @EqualsAndHashCode.Include
    private List<Customer> idClient;
    
    @ManyToMany
    @JoinTable(
        name = "scheduling_task",
        joinColumns = @JoinColumn(name = "scheduling_id"),
        inverseJoinColumns = @JoinColumn(name = "TaskType_id")
        )
    @EqualsAndHashCode.Include
    private List<TaskType> idTypeTask;
    
    private String phone;
    private LocalDate dateAgend;
    private LocalTime horsTime;
    private BigDecimal totalValueTask;

    @PrePersist // Diz que essa função será executada antes de os dados pessistirem no banco.
    @PreUpdate // Diz que essa função será executada antes de o spring atualizar a entidade.
    public void calculaateTotalValueTask(){
        this.totalValueTask = idTypeTask.stream()
        .map(TaskType::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
} 