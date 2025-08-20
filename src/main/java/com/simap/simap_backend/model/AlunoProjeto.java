package com.simap.simap_backend.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "aluno_projeto")
public class AlunoProjeto {

    @EmbeddedId
    private AlunoProjetoId id;

    @ManyToOne
    @MapsId("codAluno")
    @JoinColumn(name = "cod_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @MapsId("codProjeto")
    @JoinColumn(name = "cod_projeto", nullable = false)
    private ProjetoRecomposicao projeto;
}

@Embeddable
class AlunoProjetoId implements Serializable {

    @Column(name = "cod_aluno", length = 7)
    private String codAluno;

    @Column(name = "cod_projeto", length = 7)
    private String codProjeto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoProjetoId that = (AlunoProjetoId) o;
        return Objects.equals(codAluno, that.codAluno) &&
                Objects.equals(codProjeto, that.codProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codAluno, codProjeto);
    }
}