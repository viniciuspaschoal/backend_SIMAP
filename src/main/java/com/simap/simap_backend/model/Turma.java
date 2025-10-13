package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @Column(name = "cod_turma", length = 7)
    private String codTurma; //chave primária

    @ManyToOne
    @JoinColumn(name = "cod_escola", nullable = false) // FK para Escola
    private Escola escola;

    @Column(name = "serie", length = 1)
    private String serie;

    @Column(name = "turma", length = 1)
    private String turma;

    @Column(name = "periodo_regular", length = 20)
    private String periodoRegular;

    @Column(name = "anoletivo_r")
    private Integer anoLetivo;

    @OneToMany(mappedBy = "turma")
    private List<Diagnostico> diagnosticos;

    public Turma() {}

    public Turma(String codTurma, Escola escola, String serie, String turma, String periodoRegular, Integer anoLetivo, List<Diagnostico> diagnosticos) {
        this.codTurma = codTurma;
        this.escola = escola;
        this.serie = serie;
        this.turma = turma;
        this.periodoRegular = periodoRegular;
        this.anoLetivo = anoLetivo;
        this.diagnosticos = diagnosticos;
    }

    public String getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(String codTurma) {
        this.codTurma = codTurma;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getPeriodoRegular() {
        return periodoRegular;
    }

    public void setPeriodoRegular(String periodoRegular) {
        this.periodoRegular = periodoRegular;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turma that)) return false;
        return Objects.equals(codTurma, that.codTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codTurma);
    }
}