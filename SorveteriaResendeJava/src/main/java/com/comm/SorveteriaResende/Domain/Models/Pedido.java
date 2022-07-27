package com.comm.SorveteriaResende.Domain.Models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;
    private String nomeCliente;
    private String enderecoCliente;

}
