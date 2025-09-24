package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, String> {

    // ----------------------------
    // Campos da entidade Turma (DDL):
    // cod_turma (PK, char(7))
    // cod_escola (FK -> Escola)
    // serie (char(1))   ex: "1","2","3","4","5"
    // turma (char(1))   ex: "A","B","C"
    // periodo_regular (varchar(20)) ex: "MANHÃ","TARDE"
    // anoletivo_r (int) ex: 2025
    // ----------------------------

    // Buscar turmas por escola (usa o relacionamento: turma.escola.codEscola)
    List<Turma> findByEscola_CodEscola(String codEscola);

    // Buscar por ano letivo
    List<Turma> findByAnoLetivo(Integer anoLetivo);

    // Buscar por série
    List<Turma> findBySerie(String serie);

    // Buscar por período (ex: "MANHÃ", "TARDE")
    List<Turma> findByPeriodoRegular(String periodoRegular);

    // Combinações comuns: série + ano
    List<Turma> findBySerieAndAnoLetivo(String serie, Integer ano);

    //Identificar uma turma específica no ano (série + letra + ano)
    // Corrigido para corresponder à propriedade 'anoletivo_r'
    Turma findBySerieAndTurmaAndAnoLetivo(String serie, String turma, Integer ano);

    // Verificações rápidas
    boolean existsBySerieAndTurmaAndAnoLetivo(String serie, String turma, Integer ano);
    long countByEscola_CodEscolaAndAnoLetivo(String codEscola, Integer ano);

}
