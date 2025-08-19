package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @Column(name = "cod_aluno", length = 7)
    private String codAluno; //chave primária

    // Relacionamento com Declaração será feito depois
    @Column(name = "cod_declaracao", length = 7)
    private String codDeclaracao;

    @Column(name = "ra", length = 9, nullable = false, unique = true)
    private String ra;

    @Column(name = "nome_aluno", length = 100, nullable = false)
    private String nomeAluno;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    public Aluno(String codAluno, String codDeclaracao,
                 String ra, String nomeAluno, LocalDate dataNascimento,
                 String observacao) {
        this.codAluno = codAluno;
        this.codDeclaracao = codDeclaracao;
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

    public String getCodDeclaracao() {
        return codDeclaracao;
    }

    public void setCodDeclaracao(String codDeclaracao) {
        this.codDeclaracao = codDeclaracao;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(codAluno, aluno.codAluno) && Objects.equals(codDeclaracao, aluno.codDeclaracao) && Objects.equals(ra, aluno.ra) && Objects.equals(nomeAluno, aluno.nomeAluno) && Objects.equals(dataNascimento, aluno.dataNascimento) && Objects.equals(observacao, aluno.observacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codAluno, codDeclaracao, ra, nomeAluno, dataNascimento, observacao);
    }
}