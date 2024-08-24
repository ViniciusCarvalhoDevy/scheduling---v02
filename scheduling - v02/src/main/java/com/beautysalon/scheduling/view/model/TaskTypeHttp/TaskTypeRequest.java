package com.beautysalon.scheduling.view.model.TaskTypeHttp;

import java.math.BigDecimal;
import java.time.LocalTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class TaskTypeRequest {
    private Long id;
    private String name;
    private BigDecimal value;
    private LocalTime timeMed;
}
