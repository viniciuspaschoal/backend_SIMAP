package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

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

    // Outra classe sera criada para representar a relação de professor com turma, relacionado com a tabela (professor_turma)


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(codProfessor, professor.codProfessor) && Objects.equals(nomeProfessor, professor.nomeProfessor) && Objects.equals(emailProfessor, professor.emailProfessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProfessor, nomeProfessor, emailProfessor);
    }
}
