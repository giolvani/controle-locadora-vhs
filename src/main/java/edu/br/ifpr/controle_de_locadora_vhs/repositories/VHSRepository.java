package edu.br.ifpr.controle_de_locadora_vhs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.br.ifpr.controle_de_locadora_vhs.entities.VHS;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {

    @Query("SELECT v FROM VHS v JOIN v.categorias c WHERE c.id = :categoriaId")
    List<VHS> findByCategoriaId(@Param("categoriaId") Long categoriaId);

    List<VHS> findByTitle(String title);

    public Integer countByStatus(VHS.TapeStatus status);

    @Query("SELECT v FROM VHS v WHERE v.title = :title")
    List<VHS> buscaPorTitulo(@Param("title") String title);

}
