package com.beautysalon.scheduling.shared;

import java.math.BigDecimal;
import java.time.LocalTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class TaskTypeDTO {
    private Long id;
    private String name;
    private BigDecimal value;
    private LocalTime timeMed;
}
