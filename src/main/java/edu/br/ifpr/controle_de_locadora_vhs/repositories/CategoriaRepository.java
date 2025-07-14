package edu.br.ifpr.controle_de_locadora_vhs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.br.ifpr.controle_de_locadora_vhs.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE LOWER(c.nome) = LOWER(:nome)")
    Optional<Categoria> findByNomeIgnoreCase(@Param("nome") String nome);

    @Query("SELECT c FROM Categoria c WHERE LOWER(c.nome) = LOWER(:nome) AND c.id != :id")
    Optional<Categoria> findByNomeIgnoreCaseAndIdNot(@Param("nome") String nome, @Param("id") Long id);
}
