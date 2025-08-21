package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "resultado_diagnostico")
public class ResultadoDiagnostico {

    @Id
    @Column(name = "cod_resultdiagnostico", length = 15)
    private String codResultadoDiagnostico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_diagnostico", nullable = false)
    private Diagnostico diagnostico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_hipotese", nullable = false)
    private Hipotese hipotese;

    public ResultadoDiagnostico() {}

    public ResultadoDiagnostico(String codResultadoDiagnostico, Aluno aluno,
                                Diagnostico diagnostico, Hipotese hipotese) {
        this.codResultadoDiagnostico = codResultadoDiagnostico;
        this.aluno = aluno;
        this.diagnostico = diagnostico;
        this.hipotese = hipotese;
    }

    public String getCodResultadoDiagnostico() {
        return codResultadoDiagnostico;
    }

    public void setCodResultadoDiagnostico(String codResultadoDiagnostico) {
        this.codResultadoDiagnostico = codResultadoDiagnostico;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Hipotese getHipotese() {
        return hipotese;
    }

    public void setHipotese(Hipotese hipotese) {
        this.hipotese = hipotese;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoDiagnostico that = (ResultadoDiagnostico) o;
        return Objects.equals(codResultadoDiagnostico, that.codResultadoDiagnostico) && Objects.equals(aluno, that.aluno) && Objects.equals(diagnostico, that.diagnostico) && Objects.equals(hipotese, that.hipotese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codResultadoDiagnostico, aluno, diagnostico, hipotese);
    }
}