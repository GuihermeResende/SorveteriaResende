package com.comm.SorveteriaResende.Application.Controller;

import com.comm.SorveteriaResende.Domain.DTOs.SorveteDTO;
import com.comm.SorveteriaResende.Domain.Models.Sorvete;
import com.comm.SorveteriaResende.Domain.Services.SorveteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sorvete")
public class SorveteController {

    SorveteService _sorveteService;

    public SorveteController(SorveteService sorveteService) {
        _sorveteService = sorveteService;
    }

    @GetMapping("/list-sorvetes")
    public ResponseEntity listSorvetes() {
        try {
            return ResponseEntity.ok(_sorveteService.listSorvetes());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-id/{sorveteId}")
    public ResponseEntity findSorveteById(@PathVariable("sorveteId") Long sorveteId) {
        try {
            return ResponseEntity.ok(_sorveteService.findSorveteById(sorveteId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-descricao")
    public ResponseEntity findSorveteByDescricao(@RequestParam("descricao") String descricaoSorvete) {
        try {
            return ResponseEntity.ok(_sorveteService.findSorveteByDescricao(descricaoSorvete));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-quantidade-estoque")
    public ResponseEntity findSorveteByQuantidadeEstoque(@RequestParam("quantidadeEstoque") int quantidadeEstoque) {
        try {
            return ResponseEntity.ok(_sorveteService.findSorveteByQuantidadeEstoque(quantidadeEstoque));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-quantidade-vendas")
    public ResponseEntity findSorveteByQuantidadeVendas(@RequestParam("quantidadeVendas") int quantidadeVendas) {
        try {
            return ResponseEntity.ok(_sorveteService.findSorveteByQuantidadeVendas(quantidadeVendas));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/create-sorvete")
    public ResponseEntity createSorvete(@RequestBody SorveteDTO sorveteDTO) {
        try {
            return ResponseEntity.ok(_sorveteService.createSorvete(sorveteDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete-sorvete")
    public ResponseEntity deleteSorvete(@RequestParam ("sorveteId") Long sorveteId) {
        try{
            return ResponseEntity.ok(_sorveteService.deleteSorvete(sorveteId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
