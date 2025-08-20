package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "aluno_turma")
public class AlunoTurma {

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

    public AlunoTurma(AlunoTurmaId id, Aluno aluno, Turma turma) {
        this.id = id;
        this.aluno = aluno;
        this.turma = turma;
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
        if (o == null || getClass() != o.getClass()) return false;
        AlunoTurma that = (AlunoTurma) o;
        return Objects.equals(id, that.id) && Objects.equals(aluno, that.aluno) && Objects.equals(turma, that.turma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aluno, turma);
    }
}