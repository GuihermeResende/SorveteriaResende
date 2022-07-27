package com.comm.SorveteriaResende.Data.Repositories;

import com.comm.SorveteriaResende.Domain.Models.Sorvete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SorveteRepository extends JpaRepository<Sorvete, Long> {

    Optional<Sorvete> findByDescricao(String descricao);
    List<Sorvete> findByQuantidadeEstoque(int quantidadeEstoque);
    List<Sorvete> findByQuantidadeVendas(int quantidadeVendas);

}