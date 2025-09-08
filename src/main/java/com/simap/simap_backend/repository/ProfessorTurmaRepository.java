package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.ProfessorTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorTurmaRepository extends JpaRepository<ProfessorTurma, String> {

    // Buscar turmas de um professor específico
    List<ProfessorTurma> findByProfessor_CodProfessor(String codProfessor);
    // SELECT *
    // FROM professor_turma
    // WHERE cod_professor = ?;

    // Buscar professores de uma turma específica
    List<ProfessorTurma> findByTurma_CodTurma(String codTurma);
    // SELECT *
    // FROM professor_turma
    // WHERE cod_turma = ?;

    // Buscar professor específico em uma turma específica
    ProfessorTurma findByProfessor_CodProfessorAndTurma_CodTurma(String codProfessor, String codTurma);
    // SELECT *
    // FROM professor_turma
    // WHERE cod_professor = ? AND cod_turma = ?;

}
