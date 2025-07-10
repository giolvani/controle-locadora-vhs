package edu.br.ifpr.controle_de_locadora_vhs.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.br.ifpr.controle_de_locadora_vhs.entities.VHS;
import edu.br.ifpr.controle_de_locadora_vhs.repositories.VHSRepository;

@Service
public class VHSService {

    @Autowired
    VHSRepository vhsRepository;

    public List<VHS> findAll() {
        return vhsRepository.findAll();
    }

    public VHS save(VHS vhs) {
        if (vhs.getDataCadastro() == null) {
            vhs.setDataCadastro(LocalDate.now());
        }
        return vhsRepository.save(vhs);
    }

    public Optional<VHS> findById(Long id) {
        return vhsRepository.findById(id);
    }

    public void deleteById(Long id) {
        vhsRepository.deleteById(id);
    }

    public VHS update(Long id, VHS vhs) {
        return vhsRepository.findById(id).map(vhsup -> {
            vhs.setTitle(vhsup.getTitle());
            vhs.setImageUrl(vhsup.getImageUrl());
            vhs.setDiretor(vhsup.getDiretor());
            vhs.setCategoria(vhsup.getCategoria());
            vhs.setStatus(vhsup.getStatus());

            return vhsRepository.save(vhs);
        }).orElseThrow(() -> new RuntimeException("Fita VHS não encontrada com o ID: " + id));
    }
}
