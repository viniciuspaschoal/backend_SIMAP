package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "aluno_turma")
public class AlunoTurma implements Serializable {

    @EmbeddedId
    private AlunoTurmaId id;

    @ManyToOne
    @MapsId("codAluno")
    @JoinColumn(name = "cod_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @MapsId("codTurma")
    @JoinColumn(name = "cod_turma", nullable = false)
    private Turma turma;

    public AlunoTurma(){}

    // Construtor mais prático para criar uma nova relação AlunoTurma
    public AlunoTurma(Aluno aluno, Turma turma) {
        this.aluno = aluno;
        this.turma = turma;
        this.id = new AlunoTurmaId(aluno.getCodAluno(), turma.getCodTurma());
    }

    public AlunoTurmaId getId() {
        return id;
    }

    public void setId(AlunoTurmaId id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoTurma that = (AlunoTurma) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
