package com.comm.SorveteriaResende.Domain.Services;

import com.comm.SorveteriaResende.Domain.Models.Pedido;


import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> listPedidos();
    Optional<Pedido> findPedidoById(Long pedidoId);
    Optional<Pedido> findPedidoByDescricao(String descricaoSorvete);
    List<Optional<Pedido>> findPedidoByNomeCliente(String nomeCliente);
    Optional<Pedido> findPedidoByEnderecoCliente(String enderecoCliente);

}
