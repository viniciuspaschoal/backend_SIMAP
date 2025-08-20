package com.simap.simap_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "data_projeto")
public class DataProjeto {

    @Id
    @Column(name = "cod_dataprojeto", length = 7)
    private String codDataProjeto;

    @ManyToOne
    @JoinColumn(name = "cod_projeto", nullable = false)
    private ProjetoRecomposicao projeto;

    @Column(name = "data_projeto", nullable = false)
    private LocalDate dataProjeto;

    public DataProjeto() {}

    public DataProjeto(String codDataProjeto, ProjetoRecomposicao projeto, LocalDate dataProjeto) {
        this.codDataProjeto = codDataProjeto;
        this.projeto = projeto;
        this.dataProjeto = dataProjeto;
    }
}