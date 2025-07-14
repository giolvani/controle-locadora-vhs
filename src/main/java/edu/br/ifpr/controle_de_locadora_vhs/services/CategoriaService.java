package edu.br.ifpr.controle_de_locadora_vhs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.br.ifpr.controle_de_locadora_vhs.entities.Categoria;
import edu.br.ifpr.controle_de_locadora_vhs.entities.VHS;
import edu.br.ifpr.controle_de_locadora_vhs.repositories.CategoriaRepository;
import edu.br.ifpr.controle_de_locadora_vhs.repositories.VHSRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VHSRepository vhsRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        Optional<Categoria> existingCategoria = categoriaRepository.findByNomeIgnoreCase(categoria.getNome());
        if (existingCategoria.isPresent()) {
            throw new RuntimeException("Já existe uma categoria com o nome: " + categoria.getNome());
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoria) {
        return categoriaRepository.findById(id).map(cat -> {
            Optional<Categoria> existingCategoria = categoriaRepository.findByNomeIgnoreCaseAndIdNot(categoria.getNome(), id);
            if (existingCategoria.isPresent()) {
                throw new RuntimeException("Já existe uma categoria com o nome: " + categoria.getNome());
            }
            cat.setNome(categoria.getNome());
            return categoriaRepository.save(cat);
        }).orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    public void deleteById(Long id) {
        List<VHS> fitasAssociadas = vhsRepository.findByCategoriaId(id);
        if (!fitasAssociadas.isEmpty()) {
            throw new RuntimeException("Não é possível excluir a categoria pois há fitas VHS associadas a ela.");
        }
        categoriaRepository.deleteById(id);
    }
}
