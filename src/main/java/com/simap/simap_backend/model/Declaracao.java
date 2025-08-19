package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "declaracao")
public class Declaracao {

    @Id
    @Column(name = "cod_declaracao", length = 7)
    private String codDeclaracao; //chave primária

    @Column(name = "declaracao", length = 2, nullable = false)
    private String declaracao;

    @Column(name = "descricao_declaracao", length = 20)
    private String descricaoDeclaracao;

    public Declaracao(String codDeclaracao, String declaracao, String descricaoDeclaracao) {
        this.codDeclaracao = codDeclaracao;
        this.declaracao = declaracao;
        this.descricaoDeclaracao = descricaoDeclaracao;
    }

    public String getCodDeclaracao() {
        return codDeclaracao;
    }

    public void setCodDeclaracao(String codDeclaracao) {
        this.codDeclaracao = codDeclaracao;
    }

    public String getDeclaracao() {
        return declaracao;
    }

    public void setDeclaracao(String declaracao) {
        this.declaracao = declaracao;
    }

    public String getDescricaoDeclaracao() {
        return descricaoDeclaracao;
    }

    public void setDescricaoDeclaracao(String descricaoDeclaracao) {
        this.descricaoDeclaracao = descricaoDeclaracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Declaracao that = (Declaracao) o;
        return Objects.equals(codDeclaracao, that.codDeclaracao) && Objects.equals(declaracao, that.declaracao) && Objects.equals(descricaoDeclaracao, that.descricaoDeclaracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDeclaracao, declaracao, descricaoDeclaracao);
    }
}
