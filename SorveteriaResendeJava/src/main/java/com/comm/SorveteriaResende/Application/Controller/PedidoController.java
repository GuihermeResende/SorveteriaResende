package com.comm.SorveteriaResende.Application.Controller;

import com.comm.SorveteriaResende.Domain.Services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    PedidoService _pedidoService;

    public PedidoController(PedidoService pedidoService) {
        _pedidoService = pedidoService;
    }

    @GetMapping("/list-pedidos")
    public ResponseEntity listPedidos() {
        try {
            return ResponseEntity.ok(_pedidoService.listPedidos());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-id/{pedidoId}")
    public ResponseEntity findSorveteById(@PathVariable("pedidoId") Long pedidoId) {
        try {
            return ResponseEntity.ok(_pedidoService.findPedidoById(pedidoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-descricao")
    public ResponseEntity findPedidoByDescricao(@RequestParam("descricao") String descricaoSorvete) {
        try {
            return ResponseEntity.ok(_pedidoService.findPedidoByDescricao(descricaoSorvete));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-endereco-cliente")
    public ResponseEntity findSorveteByQuantidadeEstoque(@RequestParam("enderecoCliente") String enderecoCliente) {
        try {
            return ResponseEntity.ok(_pedidoService.findPedidoByEnderecoCliente(enderecoCliente));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-nome-cliente")
    public ResponseEntity findPedidoByNomeCliente(@RequestParam("nomeCliente") String nomeCliente) {
        try {
            return ResponseEntity.ok(_pedidoService.findPedidoByNomeCliente(nomeCliente));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
