package com.beautysalon.scheduling.view.model.ProfessionalUserHttp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProfessionalUserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String telefone;
    private String email;
    private Boolean isAdmin;
}
