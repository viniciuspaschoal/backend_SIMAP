package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.Aluno;
import com.simap.simap_backend.model.AlunoTurma;
import com.simap.simap_backend.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, String> {

    //Buscar por nome exato
    List<Aluno> findByNomeAluno(String nome);

    //Buscar aluno por parte do nome, sem diferenciar maiúscula e minúscula
    List<Aluno> findByNomeAlunoContainingIgnoreCase(String nome);

    //Buscar todos os alunos que tem em uma determinada turma
    List<Aluno> findByAlunoTurmas_Turma_CodTurma(String codTurma);

    //Busca aluno por RA - deve ser único
    Optional<Aluno> findByRa(String ra);

    //Busca alunos por declaração
    List<Aluno> findByDeclaracao_CodDeclaracao(String codDeclaracao);

    //Busca escola do aluno
    @Query("SELECT e.nomeEscola FROM Aluno a " +
            "JOIN a.alunoTurmas at " +
            "JOIN at.turma t " +
            "JOIN t.escola e " +
            "WHERE a.ra = :ra " +
            "AND t.anoLetivo = 2025")
    String findEscolaByRa(@Param("ra") String ra);
    //SELECT
    //    a.nome_aluno, a.ra, e.nome_escola, t.anoletivo_r
    //FROM aluno AS a
    //INNER JOIN
    //    aluno_turma AS alt ON a.cod_aluno = alt.cod_aluno
    //INNER JOIN
    //    turma AS t ON alt.cod_turma = t.cod_turma
    //INNER JOIN
    //    escola AS e ON t.cod_escola = e.cod_escola
    //WHERE a.ra = '110028366' AND t.anoletivo_r = 2025

    @Query("SELECT t.serie " +
            "FROM Aluno a " +
            "JOIN a.alunoTurmas at " +
            "JOIN at.turma t " +
            "WHERE a.ra = :ra " +
            "AND t.anoLetivo = 2025")
    String findSerieByRa(@Param("ra") String ra);

    @Query("SELECT t.turma " +
            "FROM Aluno a " +
            "JOIN a.alunoTurmas at " +
            "JOIN at.turma t " +
            "WHERE a.ra = :ra " +
            "AND t.anoLetivo = 2025")
    String findTurmaByRa(@Param("ra") String ra);

    @Query("SELECT t.periodoRegular " +
            "FROM Aluno a " +
            "JOIN a.alunoTurmas at " +
            "JOIN at.turma t " +
            "WHERE a.ra = :ra " +
            "AND t.anoLetivo = 2025")
    String findPeriodoByRa(@Param("ra") String ra);

}
