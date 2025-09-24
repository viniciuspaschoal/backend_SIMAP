package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.ProjetoRecomposicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRecomposicaoRepository extends JpaRepository<ProjetoRecomposicao, String> {

    // ----------------------------
    // Consultas comuns de projetos:
    // ----------------------------

    // Buscar todos os projetos de um professor
    List<ProjetoRecomposicao> findByProfessor_CodProfessor(String codProfessor);

    // Buscar projetos de um grupo específico
    List<ProjetoRecomposicao> findByGrupoProjeto_CodGrupoProjeto(String codGrupo);

    // Buscar por período
    List<ProjetoRecomposicao> findByPeriodoProjeto(String periodo);

    // Combinação: buscar projetos por professor + grupo
    List<ProjetoRecomposicao> findByProfessor_CodProfessorAndGrupoProjeto_CodGrupoProjeto(
            String codProfessor, String codGrupo);

}
