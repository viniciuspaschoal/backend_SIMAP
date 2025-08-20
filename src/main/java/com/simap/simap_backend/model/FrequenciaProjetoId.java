package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FrequenciaProjetoId implements Serializable {

    @Column(name = "cod_aluno", length = 7)
    private String codAluno;

    @Column(name = "cod_dataprojeto", length = 15)
    private String codDataProjeto;

    public FrequenciaProjetoId() {}

    public FrequenciaProjetoId(String codAluno, String codDataProjeto) {
        this.codAluno = codAluno;
        this.codDataProjeto = codDataProjeto;
    }

    public String getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(String codAluno) {
        this.codAluno = codAluno;
    }

    public String getCodDataProjeto() {
        return codDataProjeto;
    }

    public void setCodDataProjeto(String codDataProjeto) {
        this.codDataProjeto = codDataProjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequenciaProjetoId that = (FrequenciaProjetoId) o;
        return Objects.equals(codAluno, that.codAluno) && Objects.equals(codDataProjeto, that.codDataProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codAluno, codDataProjeto);
    }
}
