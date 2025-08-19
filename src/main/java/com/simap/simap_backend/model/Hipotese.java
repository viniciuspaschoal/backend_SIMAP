package com.simap.simap_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "hipotese")
public class Hipotese {

    @Id
    @Column(name = "cod_hipotese", length = 7)
    private String codHipotese;

    @Column(name = "hipotese", length = 10, nullable = false)
    private String hipotese;

    @Column(name = "descricao_hipotese")
    private String descricaoHipotese;

    public Hipotese(String codHipotese, String hipotese, String descricaoHipotese) {
        this.codHipotese = codHipotese;
        this.hipotese = hipotese;
        this.descricaoHipotese = descricaoHipotese;
    }

    public String getCodHipotese() {
        return codHipotese;
    }

    public void setCodHipotese(String codHipotese) {
        this.codHipotese = codHipotese;
    }

    public String getHipotese() {
        return hipotese;
    }

    public void setHipotese(String hipotese) {
        this.hipotese = hipotese;
    }

    public String getDescricaoHipotese() {
        return descricaoHipotese;
    }

    public void setDescricaoHipotese(String descricaoHipotese) {
        this.descricaoHipotese = descricaoHipotese;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hipotese hipotese1 = (Hipotese) o;
        return Objects.equals(codHipotese, hipotese1.codHipotese) && Objects.equals(hipotese, hipotese1.hipotese) && Objects.equals(descricaoHipotese, hipotese1.descricaoHipotese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codHipotese, hipotese, descricaoHipotese);
    }
}