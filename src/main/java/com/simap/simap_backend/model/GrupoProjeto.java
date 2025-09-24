package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "grupo_projeto")
public class GrupoProjeto {

    @Id
    @Column(name = "cod_grupoprojeto", length = 7)
    private String codGrupoProjeto;

    @Column(name = "grupoprojeto", length = 10, nullable = false)
    private String grupoProjeto;

    @Column(name = "descricao_grupoprojeto", columnDefinition = "TEXT")
    private String descricaoGrupoProjeto;

    public GrupoProjeto() {}

    public GrupoProjeto(String codGrupoProjeto, String grupoProjeto, String descricaoGrupoProjeto) {
        this.codGrupoProjeto = codGrupoProjeto;
        this.grupoProjeto = grupoProjeto;
        this.descricaoGrupoProjeto = descricaoGrupoProjeto;
    }

    public String getCodGrupoProjeto() {
        return codGrupoProjeto;
    }

    public void setCodGrupoProjeto(String codGrupoProjeto) {
        this.codGrupoProjeto = codGrupoProjeto;
    }

    public String getGrupoProjeto() {
        return grupoProjeto;
    }

    public void setGrupoProjeto(String grupoProjeto) {
        this.grupoProjeto = grupoProjeto;
    }

    public String getDescricaoGrupoProjeto() {
        return descricaoGrupoProjeto;
    }

    public void setDescricaoGrupoProjeto(String descricaoGrupoProjeto) {
        this.descricaoGrupoProjeto = descricaoGrupoProjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrupoProjeto that)) return false;
        return Objects.equals(codGrupoProjeto, that.codGrupoProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codGrupoProjeto);
    }
}
