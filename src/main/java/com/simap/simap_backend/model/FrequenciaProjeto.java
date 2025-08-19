package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "frequencia_projeto")
public class FrequenciaProjeto {

    @EmbeddedId
    private FrequenciaProjetoId id;

    @ManyToOne
    @MapsId("codAluno")
    @JoinColumn(name = "cod_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @MapsId("codProjeto")
    @JoinColumn(name = "cod_projeto", nullable = false)
    private ProjetoRecomposicao projeto;

    @Column(name = "presencas")
    private Integer presencas;

    @Column(name = "faltas")
    private Integer faltas;

    public FrequenciaProjeto() {}

    public FrequenciaProjeto(FrequenciaProjetoId id, Aluno aluno,
                             ProjetoRecomposicao projeto, Integer presencas,
                             Integer faltas) {
        this.id = id;
        this.aluno = aluno;
        this.projeto = projeto;
        this.presencas = presencas;
        this.faltas = faltas;
    }

    public FrequenciaProjetoId getId() {
        return id;
    }

    public void setId(FrequenciaProjetoId id) {
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

    public Integer getPresencas() {
        return presencas;
    }

    public void setPresencas(Integer presencas) {
        this.presencas = presencas;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequenciaProjeto that = (FrequenciaProjeto) o;
        return Objects.equals(id, that.id) && Objects.equals(aluno, that.aluno) && Objects.equals(projeto, that.projeto) && Objects.equals(presencas, that.presencas) && Objects.equals(faltas, that.faltas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aluno, projeto, presencas, faltas);
    }
}
