package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "diagnostico")
public class Diagnostico {

    @Id
    @Column(name = "cod_diagnostico", length = 7)
    private String codDiagnostico;

    @ManyToOne
    @JoinColumn(name = "cod_turma", nullable = false) // FK para Turma
    private Turma turma;

    @Column(name = "bimestre_diagnostico", length = 1, nullable = false)
    private String bimestreDiagnostico;

    @Column(name = "data_inclusaodag", nullable = false)
    private LocalDateTime dataInclusao = LocalDateTime.now();

    @Column(name = "data_atualizacaodag", nullable = false)
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    public Diagnostico(String codDiagnostico, String bimestreDiagnostico, Turma turma,
                       LocalDateTime dataInclusao, LocalDateTime dataAtualizacao) {
        this.codDiagnostico = codDiagnostico;
        this.bimestreDiagnostico = bimestreDiagnostico;
        this.turma = turma;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getCodDiagnostico() {
        return codDiagnostico;
    }

    public void setCodDiagnostico(String codDiagnostico) {
        this.codDiagnostico = codDiagnostico;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getBimestreDiagnostico() {
        return bimestreDiagnostico;
    }

    public void setBimestreDiagnostico(String bimestreDiagnostico) {
        this.bimestreDiagnostico = bimestreDiagnostico;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnostico that = (Diagnostico) o;
        return Objects.equals(codDiagnostico, that.codDiagnostico) && Objects.equals(turma, that.turma) && Objects.equals(bimestreDiagnostico, that.bimestreDiagnostico) && Objects.equals(dataInclusao, that.dataInclusao) && Objects.equals(dataAtualizacao, that.dataAtualizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDiagnostico, turma, bimestreDiagnostico, dataInclusao, dataAtualizacao);
    }
}