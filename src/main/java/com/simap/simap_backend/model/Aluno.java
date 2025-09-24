package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @Column(name = "cod_aluno", length = 7)
    private String codAluno; //chave primária

    @OneToOne
    @JoinColumn(name = "cod_declaracao", referencedColumnName = "cod_declaracao")
    private Declaracao declaracao;

    @Column(name = "ra", length = 9, nullable = false, unique = true)
    private String ra;

    @Column(name = "nome_aluno", length = 100, nullable = false)
    private String nomeAluno;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlunoTurma> alunoTurmas;

    // Construtor vazio para o JPA
    public Aluno() {}

    public Aluno(String codAluno, Declaracao declaracao,
                 String ra, String nomeAluno, LocalDate dataNascimento,
                 String observacao) {
        this.codAluno = codAluno;
        this.declaracao = declaracao;
        this.ra = ra;
        this.nomeAluno = nomeAluno;
        this.dataNascimento = dataNascimento;
        this.observacao = observacao;
    }

    public String getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(String codAluno) {
        this.codAluno = codAluno;
    }

    public Declaracao getDeclaracao() {
        return declaracao;
    }

    public void setDeclaracao(Declaracao declaracao) {
        this.declaracao = declaracao;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Set<AlunoTurma> getAlunoTurmas() {
        return alunoTurmas;
    }

    public void setAlunoTurmas(Set<AlunoTurma> alunoTurmas) {
        this.alunoTurmas = alunoTurmas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(codAluno, aluno.codAluno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codAluno);
    }
}
