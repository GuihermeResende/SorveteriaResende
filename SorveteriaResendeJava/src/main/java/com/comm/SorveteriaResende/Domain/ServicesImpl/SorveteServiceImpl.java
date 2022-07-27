package com.comm.SorveteriaResende.Domain.ServicesImpl;

import com.comm.SorveteriaResende.Data.Repositories.SorveteRepository;
import com.comm.SorveteriaResende.Domain.DTOs.SorveteDTO;
import com.comm.SorveteriaResende.Domain.Models.Sorvete;
import com.comm.SorveteriaResende.Domain.Services.SorveteService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class SorveteServiceImpl implements SorveteService {

    SorveteRepository _sorveteRepository;

    public SorveteServiceImpl(SorveteRepository sorveteRepository) {
        _sorveteRepository = sorveteRepository;
    }

    @Override
    public List<Sorvete> listSorvetes() {
        return _sorveteRepository.findAll();
    }

    @Override
    public Sorvete createSorvete(SorveteDTO sorveteDTO) {
        Sorvete findSorvete = _sorveteRepository.findByDescricao(sorveteDTO.getDescricao()).orElse(null);
        if (findSorvete != null) {
            throw new IllegalArgumentException("Já existe algum sorvete com esta descrição");
        }

        Sorvete newSorvete = new Sorvete();
        newSorvete.setDescricao(sorveteDTO.getDescricao());
        newSorvete.setQuantidadeEstoque(sorveteDTO.getQuantidadeEstoque());
        newSorvete.setQuantidadeVendas(sorveteDTO.getQuantidadeVendas());
        newSorvete.setImage(Base64.getDecoder().decode(sorveteDTO.getImage()));

        _sorveteRepository.save(newSorvete);
        return newSorvete;
    }

    @Override
    public SorveteDTO findSorveteById(Long sorveteId) {
        Sorvete findIdSorvete = _sorveteRepository.findById(sorveteId).get();
        if (findIdSorvete != null) {
            throw new IllegalArgumentException("Não existe sorvete com este Id");
        }

        return SorveteDTO.toDTO(findIdSorvete);
    }

    @Override
    public SorveteDTO findSorveteByDescricao(String descricaoSorvete) {
       Sorvete findDescricaoSorvete = _sorveteRepository.findByDescricao(descricaoSorvete).get();
       if (findDescricaoSorvete == null) {
           throw new IllegalArgumentException("Não existe sorvete com essa descrição");
       }

       return SorveteDTO.toDTO(findDescricaoSorvete);
    }

    @Override
    public List<SorveteDTO> findSorveteByQuantidadeEstoque(int quantidadeEstoque) {
        List<Sorvete> findQuantidadeEstoque = _sorveteRepository.findByQuantidadeEstoque(quantidadeEstoque);
        if (findQuantidadeEstoque.isEmpty()) {
            throw new IllegalArgumentException("Não existe estoque para este produto!");
        }

        List<SorveteDTO> sorveteDTO = new ArrayList<>();

        for (Sorvete sorvete : findQuantidadeEstoque) {
            sorveteDTO.add(SorveteDTO.toDTO(sorvete));
        }

        return sorveteDTO;
    }

    @Override
    public List<SorveteDTO> findSorveteByQuantidadeVendas(int quantidadeVendas) {
        List<Sorvete> findQuantidadeVendas = _sorveteRepository.findByQuantidadeVendas(quantidadeVendas);
        if (findQuantidadeVendas == null) {
            throw new IllegalArgumentException("Não existe vendas desse produto!");
        }

        List<SorveteDTO> sorveteDTO = new ArrayList<>();

        for (Sorvete sorvete: findQuantidadeVendas) {
            sorveteDTO.add(SorveteDTO.toDTO(sorvete));
        }

        return sorveteDTO;
    }

    @Override
    public Boolean deleteSorvete(Long sorveteId) {
        Sorvete findSorvete = _sorveteRepository.findById(sorveteId).get();
        if (findSorvete == null) {
            throw new IllegalArgumentException("Este sorvete não existe");
        }

         _sorveteRepository.delete(findSorvete);

        return true;
    }

}
