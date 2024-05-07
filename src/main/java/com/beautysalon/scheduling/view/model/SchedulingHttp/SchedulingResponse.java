package com.beautysalon.scheduling.view.model.SchedulingHttp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.beautysalon.scheduling.model.Constumer;
import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.model.TypeTask;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class SchedulingResponse {
    private Long id;
    private List<ProfessionalUser> idUserProfissional;
    private List<Constumer> idClient;
    private List<TypeTask> idTypeTask;
    private String phone;
    private LocalDate dateAgend;
    private LocalTime horsTime;
    private BigDecimal totalValueService;
}
