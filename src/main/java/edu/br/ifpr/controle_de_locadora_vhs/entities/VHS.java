package edu.br.ifpr.controle_de_locadora_vhs.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vhs")
public class VHS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String imageUrl; // Caminho ou URL da imagem (opcional)
    private String diretor;
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private TapeStatus status;

    public Object getDataCadastro() {
        throw new UnsupportedOperationException("Unimplemented method 'getDataCadastro'");
    }

    public void setDataCadastro(LocalDate now) {
        throw new UnsupportedOperationException("Unimplemented method 'setDataCadastro'");
    }

    public enum TapeStatus {
        DISPONIVEL,
        EMPRESTADA,
        INDISPONIVEL
    }
}
