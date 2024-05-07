package com.beautysalon.scheduling.shared;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class SchedulingDTO {
    private Long id;
    private List<ProfessionalUserDTO> idUserProfissional;
    private List<ConstumerDTO> idClient;
    private List<TypeTaskDTO> idTypeTask;
    private String phone;
    private LocalDate dateAgend;
    private LocalTime horsTime;
    private BigDecimal totalValueService;
}
