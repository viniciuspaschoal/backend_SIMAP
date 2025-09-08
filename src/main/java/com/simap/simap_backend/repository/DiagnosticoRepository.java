package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, String> {

    // Buscar diagnóstico por código
    Diagnostico findByCodDiagnostico(String codDiagnostico);
    //SELECT *
    //FROM diagnostico
    //WHERE cod_diagnostico = ?

    // Buscar todos os diagnósticos de uma turma
    List<Diagnostico> findByTurma_CodTurma(String codTurma);
    //SELECT *
    //FROM diagnostico
    //WHERE cod_turma = ?

    // Buscar diagnósticos de uma turma em um bimestre específico
    List<Diagnostico> findByTurma_CodTurmaAndBimestreDiagnostico(String codTurma, String bimestre);
    //SELECT *
    //FROM diagnostico
    //WHERE cod_turma = ? AND bimestre_diagnostico = ?

    // Buscar diagnósticos dentro de um intervalo de datas
    //List<Diagnostico> findByDataInclusaoBetween(LocalDateTime start, LocalDateTime end);
    // SELECT *
    // FROM diagnostico
    // WHERE data_inclusaodag BETWEEN ? AND ?;

    // 5. Exemplo usando @Query para demonstrar JOIN (caso queira relacionar com Turma)
    //@Query("SELECT d FROM Diagnostico d JOIN d.turma t WHERE t.codTurma = :codTurma AND d.dataInclusao BETWEEN :start AND :end")
    //List<Diagnostico> findByTurmaAndDateRange(@Param("codTurma") String codTurma,
    //                                          @Param("start") LocalDateTime start,
    //                                          @Param("end") LocalDateTime end);
}
