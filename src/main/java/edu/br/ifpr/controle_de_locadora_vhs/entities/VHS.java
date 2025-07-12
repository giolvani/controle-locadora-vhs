package edu.br.ifpr.controle_de_locadora_vhs.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "categorias") 
@Table(name = "vhs")
public class VHS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "diretor")
    private String diretor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TapeStatus status;

    @ManyToMany
    @JoinTable(name = "categoria_vhs", joinColumns = @JoinColumn(name = "vhs_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    @Column(name = "data_cadastro")
    private LocalDate registrationDate;

    public enum TapeStatus {
        DISPONIVEL,
        EMPRESTADA,
        INDISPONIVEL
    }
}
