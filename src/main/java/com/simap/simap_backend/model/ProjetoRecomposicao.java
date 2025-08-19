package com.simap.simap_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "projeto_recomposicao")
public class ProjetoRecomposicao {

    @Id
    @Column(name = "cod_projeto", length = 7)
    private String codProjeto;

    @ManyToOne
    @JoinColumn(name = "cod_professor", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "cod_grupoprojeto", nullable = false)
    private GrupoProjeto grupoProjeto;

    @Column(name = "periodo_projeto", length = 20)
    private String periodoProjeto;
}