package com.beautysalon.scheduling.view.model.TaskTypeHttp;

import java.math.BigDecimal;
import java.time.LocalTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class TaskTypeResponse {
    private Long id;
    private String name;
    private BigDecimal value;
    private LocalTime timeMed;
}
