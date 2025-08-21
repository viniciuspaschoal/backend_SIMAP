package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "frequencia_projeto")
public class FrequenciaProjeto {

    @EmbeddedId
    private FrequenciaProjetoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codAluno")
    @JoinColumn(name = "cod_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codDataProjeto")
    @JoinColumn(name = "cod_dataprojeto", nullable = false)
    private DataProjeto dataProjeto;

    @Column(name = "frequencia_p", nullable = false)
    private Boolean presente;

    public FrequenciaProjeto() {}

    public FrequenciaProjeto(Aluno aluno, DataProjeto dataProjeto, Boolean presente) {
        this.aluno = aluno;
        this.dataProjeto = dataProjeto;
        this.presente = presente;
        this.id = new FrequenciaProjetoId(aluno.getCodAluno(), dataProjeto.getCodDataProjeto());
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

    public DataProjeto getDataProjeto() {
        return dataProjeto;
    }

    public void setDataProjeto(DataProjeto dataProjeto) {
        this.dataProjeto = dataProjeto;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequenciaProjeto that = (FrequenciaProjeto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}