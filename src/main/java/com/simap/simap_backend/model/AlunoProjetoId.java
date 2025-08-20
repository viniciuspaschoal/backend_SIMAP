package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Classe que representa a chave primária composta da tabela aluno_projeto.
// A PK é formada por (cod_aluno, cod_projeto).
@Embeddable
public class AlunoProjetoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cod_aluno", length = 7)
    private String codAluno;

    @Column(name = "cod_projeto", length = 7)
    private String codProjeto;

    public AlunoProjetoId() {}

    public AlunoProjetoId(String codAluno, String codProjeto) {
        this.codAluno = codAluno;
        this.codProjeto = codProjeto;
    }

    public String getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(String codAluno) {
        this.codAluno = codAluno;
    }

    public String getCodProjeto() {
        return codProjeto;
    }

    public void setCodProjeto(String codProjeto) {
        this.codProjeto = codProjeto;
    }

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
