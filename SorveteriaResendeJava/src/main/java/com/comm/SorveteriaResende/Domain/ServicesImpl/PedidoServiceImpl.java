package com.comm.SorveteriaResende.Domain.ServicesImpl;

import com.comm.SorveteriaResende.Data.Repositories.PedidoRepository;
import com.comm.SorveteriaResende.Domain.Models.Pedido;
import com.comm.SorveteriaResende.Domain.Services.PedidoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    PedidoRepository _pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        _pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listPedidos() {
        return _pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findPedidoById(Long pedidoId) {
        Optional<Pedido> findIdPedido = _pedidoRepository.findById(pedidoId);
        if (!findIdPedido.isPresent()) {
            throw new IllegalArgumentException("Este pedido não existe");
        }

        return findIdPedido;
    }

    @Override
    public Optional<Pedido> findPedidoByDescricao(String descricaoSorvete) {
        Optional<Pedido> findDescricaoPedido = _pedidoRepository.findByDescricao(descricaoSorvete);
        if (!findDescricaoPedido.isPresent()) {
            throw new IllegalArgumentException("Não existe pedido com esta descrição");
        }

        return findDescricaoPedido;
    }

    @Override
    public List<Optional<Pedido>> findPedidoByNomeCliente(String nomeCliente) {
        List<Optional<Pedido>> findClientePedido = _pedidoRepository.findByNomeCliente(nomeCliente);
        if (!findClientePedido.isEmpty()) {
            throw new IllegalArgumentException("Não existe cliente com este nome");
        }

        return findClientePedido;
    }

    @Override
    public Optional<Pedido> findPedidoByEnderecoCliente(String enderecoCliente) {
        Optional<Pedido> findPedidoByEnderecoCliente = _pedidoRepository.findByEnderecoCliente(enderecoCliente);
        if (!findPedidoByEnderecoCliente.isPresent()) {
            throw new IllegalArgumentException("Não existe pedido com este endereço");
        }

        return findPedidoByEnderecoCliente;
    }

}
