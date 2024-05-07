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

import jakarta.persistence.OneToMany;
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
    
    @OneToMany
    @JoinColumn(name = "ProfessionalUser_id")
    @EqualsAndHashCode.Include
    private List<ProfessionalUser> idUserProfissional;
    
    @OneToMany
    @JoinColumn(name = "Client_id")
    @EqualsAndHashCode.Include
    private List<Constumer> idClient;
    
    @OneToMany
    @JoinColumn(name = "TypeTask_id")
    @EqualsAndHashCode.Include
    private List<TypeTask> idTypeTask;
    
    private String phone;
    private LocalDate dateAgend;
    private LocalTime horsTime;
    private BigDecimal totalValueService;
}