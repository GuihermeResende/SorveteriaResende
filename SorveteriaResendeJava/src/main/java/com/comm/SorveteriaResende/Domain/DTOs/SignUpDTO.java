package com.comm.SorveteriaResende.Domain.DTOs;

import lombok.Data;

@Data
public class SignUpDTO {

    private String nome;
    private String username;
    private String email;
    private String senha;
    private String senhaConfirmada;
    private String cpf;
    private String endereco;

}

