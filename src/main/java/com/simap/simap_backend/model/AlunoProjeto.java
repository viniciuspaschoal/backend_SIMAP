package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "aluno_projeto")
public class AlunoProjeto {

    @EmbeddedId
    private AlunoProjetoId id;

    @ManyToOne
    @MapsId("codAluno") // mapeia parte do ID composta
    @JoinColumn(name = "cod_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @MapsId("codProjeto") // mapeia parte do ID composta
    @JoinColumn(name = "cod_projeto", nullable = false)
    private ProjetoRecomposicao projeto;

    public AlunoProjeto() {}

    public AlunoProjeto(AlunoProjetoId id, Aluno aluno, ProjetoRecomposicao projeto) {
        this.id = id;
        this.aluno = aluno;
        this.projeto = projeto;
    }

    public AlunoProjetoId getId() {
        return id;
    }

    public void setId(AlunoProjetoId id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public ProjetoRecomposicao getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoRecomposicao projeto) {
        this.projeto = projeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlunoProjeto)) return false;
        AlunoProjeto that = (AlunoProjeto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
