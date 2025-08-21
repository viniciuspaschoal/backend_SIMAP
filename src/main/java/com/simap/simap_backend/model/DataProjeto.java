package com.simap.simap_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="data_projeto")
public class DataProjeto {

    @Id
    @Column(name="cod_dataprojeto", length=15)
    private String codDataProjeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cod_projeto", nullable=false)
    private ProjetoRecomposicao projeto;

    @Column(name="data_projeto", nullable=false)
    private LocalDate dataProjeto;

    public DataProjeto() {}

    public DataProjeto(String codDataProjeto, ProjetoRecomposicao projeto, LocalDate dataProjeto) {
        this.codDataProjeto = codDataProjeto;
        this.projeto = projeto;
        this.dataProjeto = dataProjeto;
    }

    public String getCodDataProjeto() {
        return codDataProjeto;
    }

    public void setCodDataProjeto(String codDataProjeto) {
        this.codDataProjeto = codDataProjeto;
    }

    public ProjetoRecomposicao getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoRecomposicao projeto) {
        this.projeto = projeto;
    }

    public LocalDate getDataProjeto() {
        return dataProjeto;
    }

    public void setDataProjeto(LocalDate dataProjeto) {
        this.dataProjeto = dataProjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProjeto that = (DataProjeto) o;
        return Objects.equals(codDataProjeto, that.codDataProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDataProjeto);
    }
}
