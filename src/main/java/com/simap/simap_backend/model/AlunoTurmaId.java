package com.simap.simap_backend.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlunoTurmaId implements Serializable {

    private Long codAluno;
    private Long codTurma;

    public AlunoTurmaId() {}

    public AlunoTurmaId(Long codAluno, Long codTurma) {
        this.codAluno = codAluno;
        this.codTurma = codTurma;
    }

    public Long getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Long codAluno) {
        this.codAluno = codAluno;
    }

    public Long getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(Long codTurma) {
        this.codTurma = codTurma;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlunoTurmaId that = (AlunoTurmaId) o;
        return Objects.equals(codAluno, that.codAluno) && Objects.equals(codTurma, that.codTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codAluno, codTurma);
    }
}
