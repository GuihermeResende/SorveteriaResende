package com.comm.SorveteriaResende.Domain.Services;

import com.comm.SorveteriaResende.Domain.DTOs.SorveteDTO;
import com.comm.SorveteriaResende.Domain.Models.Sorvete;
import java.util.List;
import java.util.Optional;

public interface SorveteService {

    List<Sorvete> listSorvetes();
    Sorvete createSorvete(SorveteDTO sorveteDTO);
    SorveteDTO findSorveteById(Long sorveteId);
    SorveteDTO findSorveteByDescricao(String descricaoSorvete);
    List<SorveteDTO> findSorveteByQuantidadeEstoque(int quantidadeEstoque);
    List<SorveteDTO> findSorveteByQuantidadeVendas(int quantidadeVendas);
    Boolean deleteSorvete(Long sorveteId);

}
