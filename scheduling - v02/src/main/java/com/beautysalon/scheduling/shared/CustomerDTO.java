package com.beautysalon.scheduling.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CustomerDTO
{

    private Long id;
    private String name;
}
