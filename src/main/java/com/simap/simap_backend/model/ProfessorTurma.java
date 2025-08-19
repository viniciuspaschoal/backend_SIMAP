package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "professor_turma")
public class ProfessorTurma {

    @EmbeddedId
    private ProfessorTurmaId id;

    @ManyToOne
    @MapsId("codProfessor") // mapeia a parte do ID que é professor
    @JoinColumn(name = "cod_professor", nullable = false)
    private Professor professor;

    @ManyToOne
    @MapsId("codTurma") // mapeia a parte do ID que é turma
    @JoinColumn(name = "cod_turma", nullable = false)
    private Turma turma;

    public ProfessorTurma(ProfessorTurmaId id, Professor professor, Turma turma) {
        this.id = id;
        this.professor = professor;
        this.turma = turma;
    }

    public ProfessorTurmaId getId() {
        return id;
    }

    public void setId(ProfessorTurmaId id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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
        ProfessorTurma that = (ProfessorTurma) o;
        return Objects.equals(id, that.id) && Objects.equals(professor, that.professor) && Objects.equals(turma, that.turma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professor, turma);
    }
}