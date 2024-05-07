package com.beautysalon.scheduling.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ProfessionalUser")
@Table(name = "ProfessionalUser")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProfessionalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String telefone;
    private String email;
    private Boolean isAdmin;

}
