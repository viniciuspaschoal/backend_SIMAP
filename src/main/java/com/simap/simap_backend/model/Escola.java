package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "escola")
public class Escola {

    @Id
    @Column(name = "codEscola", length = 7)
    private String codEscola; //chave primária

    @Column(name = "nome_escola", length = 200)
    private String nomeEscola;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "end_rua", length = 100)
    private String endRua;

    @Column(name = "end_bairro", length = 100)
    private String endBairro;

    @Column(name = "end_numero", length = 10)
    private String endNumero;

    @Column(name = "end_cidade", length = 100)
    private String endCidade;

    @Column(name = "end_estado", length = 2)
    private String endEstado;

    @Column(name = "end_cep", length = 10)
    private String endCep;

    @Column(name = "end_complemento")
    private String endCompleto;

    //Relacionamento com Turma
    @OneToMany(mappedBy = "escola")
    private List<Turma> turmas;

    public Escola(String codEscola, String nomeEscola, String telefone,
                  String endRua, String endBairro, String endNumero,
                  String endCidade, String endCep, String endEstado,
                  String endCompleto, List<Turma> turmas) {
        this.codEscola = codEscola;
        this.nomeEscola = nomeEscola;
        this.telefone = telefone;
        this.endRua = endRua;
        this.endBairro = endBairro;
        this.endNumero = endNumero;
        this.endCidade = endCidade;
        this.endCep = endCep;
        this.endEstado = endEstado;
        this.endCompleto = endCompleto;
        this.turmas = turmas;
    }

    public String getCodEscola() {
        return codEscola;
    }

    public void setCodEscola(String codEscola) {
        this.codEscola = codEscola;
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndEstado() {
        return endEstado;
    }

    public void setEndEstado(String endEstado) {
        this.endEstado = endEstado;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndCompleto() {
        return endCompleto;
    }

    public void setEndCompleto(String endCompleto) {
        this.endCompleto = endCompleto;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Escola escola = (Escola) o;
        return Objects.equals(codEscola, escola.codEscola) && Objects.equals(nomeEscola, escola.nomeEscola) && Objects.equals(telefone, escola.telefone) && Objects.equals(endRua, escola.endRua) && Objects.equals(endBairro, escola.endBairro) && Objects.equals(endNumero, escola.endNumero) && Objects.equals(endCidade, escola.endCidade) && Objects.equals(endEstado, escola.endEstado) && Objects.equals(endCep, escola.endCep) && Objects.equals(endCompleto, escola.endCompleto) && Objects.equals(turmas, escola.turmas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEscola, nomeEscola, telefone, endRua, endBairro, endNumero, endCidade, endEstado, endCep, endCompleto, turmas);
    }
}
