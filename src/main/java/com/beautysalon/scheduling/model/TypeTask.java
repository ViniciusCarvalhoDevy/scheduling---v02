package com.beautysalon.scheduling.model;

import java.math.BigDecimal;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "TypeTask")
@Table(name = "TypeTask")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class TypeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal value;
    private LocalTime timeMed;
}
