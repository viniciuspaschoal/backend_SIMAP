package com.simap.simap_backend.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FrequenciaProjetoId implements Serializable {

    private Long codAluno;
    private Long codProjeto;

    public FrequenciaProjetoId() {}

    public FrequenciaProjetoId(Long codAluno, Long codProjeto) {
        this.codAluno = codAluno;
        this.codProjeto = codProjeto;
    }

    public Long getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Long codAluno) {
        this.codAluno = codAluno;
    }

    public Long getCodProjeto() {
        return codProjeto;
    }

    public void setCodProjeto(Long codProjeto) {
        this.codProjeto = codProjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequenciaProjetoId that = (FrequenciaProjetoId) o;
        return Objects.equals(codAluno, that.codAluno) && Objects.equals(codProjeto, that.codProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codAluno, codProjeto);
    }
}