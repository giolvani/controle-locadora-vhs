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
        if (vhs.getRegistrationDate() == null) {
            vhs.setRegistrationDate(LocalDate.now());
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
        return vhsRepository.findById(id).map(existingVhs -> {
            existingVhs.setTitle(vhs.getTitle());
            existingVhs.setImageUrl(vhs.getImageUrl());
            existingVhs.setDiretor(vhs.getDiretor());
            existingVhs.setCategoria(vhs.getCategoria());
            existingVhs.setStatus(vhs.getStatus());

            return vhsRepository.save(existingVhs);
        }).orElseThrow(() -> new RuntimeException("Fita VHS não encontrada com o ID: " + id));
    }
}
