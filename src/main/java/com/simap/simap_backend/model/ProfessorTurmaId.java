package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

// Classe que representa a chave primária composta da tabela professor_turma.
// A PK é formada pelos campos (cod_professor, cod_turma).
@Embeddable
public class ProfessorTurmaId implements Serializable {

    // Parte 1 da chave primária composta
    @Column(name = "cod_professor", length = 7)
    private String codProfessor;

    // Parte 2 da chave primária composta
    @Column(name = "cod_turma", length = 7)
    private String codTurma;

    public ProfessorTurmaId(){}

    public ProfessorTurmaId(String codProfessor, String codTurma) {
        this.codProfessor = codProfessor;
        this.codTurma = codTurma;
    }

    public String getCodProfessor() {
        return codProfessor;
    }

    public void setCodProfessor(String codProfessor) {
        this.codProfessor = codProfessor;
    }

    public String getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(String codTurma) {
        this.codTurma = codTurma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorTurmaId that = (ProfessorTurmaId) o;
        return Objects.equals(codProfessor, that.codProfessor) && Objects.equals(codTurma, that.codTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProfessor, codTurma);
    }
}
