package com.comm.SorveteriaResende.Domain.DTOs;

import com.comm.SorveteriaResende.Domain.Models.Sorvete;
import lombok.Data;

@Data
public class SorveteDTO {

    private Long id;
    private String descricao;
    private int quantidadeEstoque;
    private int quantidadeVendas;
    private String image;

    public static SorveteDTO toDTO(Sorvete sorvete) {
        SorveteDTO sorveteDTO = new SorveteDTO();
        sorveteDTO.id = sorvete.getId();
        sorveteDTO.descricao = sorvete.getDescricao();
        sorveteDTO.quantidadeEstoque = sorvete.getQuantidadeEstoque();
        sorveteDTO.quantidadeVendas = sorvete.getQuantidadeVendas();

        return sorveteDTO;
    }

}
