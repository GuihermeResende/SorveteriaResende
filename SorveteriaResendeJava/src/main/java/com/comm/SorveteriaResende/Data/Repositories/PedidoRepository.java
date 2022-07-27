package com.comm.SorveteriaResende.Data.Repositories;

import com.comm.SorveteriaResende.Domain.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByDescricao(String nome);
    List<Optional<Pedido>> findByNomeCliente(String nomeCliente);
    Optional<Pedido> findByEnderecoCliente(String enderecoCliente);

}
