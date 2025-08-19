package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "frequencia_regular")
public class FrequenciaRegular {
    @Id
    @Column(name = "cod_freqregular", length = 15)
    private String codFreqRegular;

    @ManyToOne
    @JoinColumn(name = "cod_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "cod_turma", nullable = false)
    private Turma turma;

    @Column(name = "bimestre_r", length = 1, nullable = false)
    private String bimestreR;       // '1','2','3','4'

    @Column(name = "frequencia_r", length = 3, nullable = false)
    private String frequenciaR;     // ex: "7", "10", "0" (string conforme DDL)

    public FrequenciaRegular() {}

    public String getCodFreqRegular() {
        return codFreqRegular;
    }

    public void setCodFreqRegular(String codFreqRegular) {
        this.codFreqRegular = codFreqRegular;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getBimestreR() {
        return bimestreR;
    }

    public void setBimestreR(String bimestreR) {
        this.bimestreR = bimestreR;
    }

    public String getFrequenciaR() {
        return frequenciaR;
    }

    public void setFrequenciaR(String frequenciaR) {
        this.frequenciaR = frequenciaR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequenciaRegular that = (FrequenciaRegular) o;
        return Objects.equals(codFreqRegular, that.codFreqRegular) && Objects.equals(aluno, that.aluno) && Objects.equals(turma, that.turma) && Objects.equals(bimestreR, that.bimestreR) && Objects.equals(frequenciaR, that.frequenciaR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codFreqRegular, aluno, turma, bimestreR, frequenciaR);
    }
}
