package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.ResultadoDiagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoDiagnosticoRepository extends JpaRepository<ResultadoDiagnostico, String> {

    // Buscar todos os resultados de um aluno específico
    List<ResultadoDiagnostico> findByAluno_CodAluno(String codAluno);
    //SELECT *
    // FROM resultado_diagnostico
    //WHERE cod_aluno = ?;

    // Buscar todos os resultados de um diagnóstico específico
    List<ResultadoDiagnostico> findByDiagnostico_CodDiagnostico(String codDiagnostico);
    //SELECT *
    //FROM resultado_diagnostico
    //WHERE cod_diagnostico = ?;

    // Buscar todos os resultados de uma hipótese específica
    List<ResultadoDiagnostico> findByHipotese_CodHipotese(String codHipotese);
    //SELECT *
    //FROM resultado_diagnostico
    //WHERE cod_hipotese = ?;

    // Buscar resultados de um aluno em um diagnóstico específico
    List<ResultadoDiagnostico> findByAluno_CodAlunoAndDiagnostico_CodDiagnostico(String codAluno, String codDiagnostico);
    //SELECT *
    //FROM resultado_diagnostico
    //WHERE cod_aluno = ? AND cod_diagnostico = ?;

    // Buscar resultados de todos os alunos de uma turma em um diagnóstico específico
    List<ResultadoDiagnostico> findByDiagnostico_CodDiagnosticoAndAluno_AlunoTurmas_Turma_CodTurma(String codDiagnostico, String codTurma);
    //SELECT rd.*
    //FROM resultado_diagnostico rd
    //JOIN aluno_turma at ON rd.cod_aluno = at.cod_aluno
    //WHERE rd.cod_diagnostico = ? AND at.cod_turma = ?;
}
