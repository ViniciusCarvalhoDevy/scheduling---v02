package com.beautysalon.scheduling.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ConstumerDTO
{

    private Long id;
    private String name;
}
