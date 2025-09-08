package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.FrequenciaRegular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrequenciaRegularRepository extends JpaRepository<FrequenciaRegular, String> {

    // Buscar frequências de um aluno específico em tsimodas as turmas
    List<FrequenciaRegular> findByAluno_CodAluno(String codAluno);
    // SELECT *
    // FROM frequencia_regular
    // WHERE cod_aluno = ?;

    // Buscar frequências de uma turma específica
    List<FrequenciaRegular> findByTurma_CodTurma(String codTurma);
    // SELECT *
    // FROM frequencia_regular
    // WHERE cod_turma = ?;

    // Buscar frequências de um aluno específico em uma turma
    List<FrequenciaRegular> findByAluno_CodAlunoAndTurma_CodTurma(String codAluno, String codTurma);
    // SELECT *
    // FROM frequencia_regular
    // WHERE cod_aluno = ? AND cod_turma = ?;

    // Buscar frequências de uma turma específica em um bimestre
    List<FrequenciaRegular> findByTurma_CodTurmaAndBimestreR(String codTurma, String bimestre);
    // SELECT *
    // FROM frequencia_regular
    // WHERE cod_turma = ? AND bimestre_r = ?;

    // Buscar frequências de todos os alunos de uma turma específica em um bimestre
    List<FrequenciaRegular> findByTurma_CodTurmaAndBimestreRAndFrequenciaR(String codTurma, String bimestre, String frequencia);
    // SELECT *
    // FROM frequencia_regular
    // WHERE cod_turma = ? AND bimestre_r = ? AND frequencia_r = ?;
}
