package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.GrupoProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoProjetoRepository extends JpaRepository<GrupoProjeto, String> {
    // ----------------------------
    // Consultas comuns para grupos:
    // ----------------------------

    // Buscar grupo pelo nome exato (ex: "ALF")
    GrupoProjeto findByGrupoProjeto(String nome);

    // Buscar grupos que contenham parte do nome (ex: "MAT" → traz "MAT1", "MAT2")
    List<GrupoProjeto> findByGrupoProjetoContainingIgnoreCase(String nome);

    // Buscar por parte da descrição
    List<GrupoProjeto> findByDescricaoGrupoProjetoContainingIgnoreCase(String descricao);
}
