package com.beautysalon.scheduling.model.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessages {
    private String title;
    private Integer status;
    private String mensagem;

    public ErrorMessages(String title, Integer status, String mensagem) {
        this.title = title;
        this.status = status;
        this.mensagem = mensagem;
    }
}
