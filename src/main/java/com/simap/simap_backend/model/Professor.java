package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @Column(name = "cod_professor", length = 7)
    private String codProfessor; //chave primária

    @Column(name = "nome_professor", length = 200, nullable = false)
    private String nomeProfessor;

    @Column(name = "email_professor", length = 100, nullable = false, unique = true)
    private String emailProfessor;

    @OneToMany(mappedBy = "professor") // 'professor' é o nome do campo em ProfessorTurma
    private Set<ProfessorTurma> professorTurmas;

    public Professor() {}

    public Professor(String codProfessor, String nomeProfessor, String emailProfessor) {
        this.codProfessor = codProfessor;
        this.nomeProfessor = nomeProfessor;
        this.emailProfessor = emailProfessor;
    }

    public String getCodProfessor() {
        return codProfessor;
    }

    public void setCodProfessor(String codProfessor) {
        this.codProfessor = codProfessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }

    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }

    public Set<ProfessorTurma> getProfessorTurmas() {
        return professorTurmas;
    }

    public void setProfessorTurmas(Set<ProfessorTurma> professorTurmas) {
        this.professorTurmas = professorTurmas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor that)) return false;
        return Objects.equals(codProfessor, that.codProfessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProfessor);
    }
}
