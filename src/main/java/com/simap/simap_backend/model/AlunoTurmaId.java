package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

// Classe que representa a chave primária composta da tabela aluno_turma.
// A PK é formada pelos campos (cod_aluno, cod_turma).
@Embeddable
public class AlunoTurmaId implements Serializable {

    private static final long serialVersionUID = 1L;

    // Parte 1 da chave primária composta
    @Column(name = "cod_aluno", length = 7)
    private String codAluno;

    // Parte 2 da chave primária composta
    @Column(name = "cod_turma", length = 7)
    private String codTurma;

    public AlunoTurmaId() {}

    public AlunoTurmaId(String codAluno, String codTurma) {
        this.codAluno = codAluno;
        this.codTurma = codTurma;
    }

    public String getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(String codAluno) {
        this.codAluno = codAluno;
    }

    public String getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(String codTurma) {
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
