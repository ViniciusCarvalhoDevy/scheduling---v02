package com.beautysalon.scheduling.view.model.SchedulingHttp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class SchedulingRequest {
    private Long id;
    private List<Long> idUserProfissional;
    private List<Long> idClient;
    private List<Long> idTypeTask;
    private String phone;
    private LocalDate dateAgend;
    private LocalTime horsTime;
}
