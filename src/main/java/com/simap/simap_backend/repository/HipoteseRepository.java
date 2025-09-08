package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.Hipotese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HipoteseRepository extends JpaRepository<Hipotese, String> {

    // Buscar hipótese pelo código da hipótese
    Hipotese findByCodHipotese(String codHipotese);
    // SELECT *
    // FROM hipotese
    // WHERE cod_hipotese = ?;

    // Buscar todas as hipóteses
    List<Hipotese> findAll();
    // SELECT *
    // hipotese;

    // Buscar hipótese pelo nome
    List<Hipotese> findByHipoteseContainingIgnoreCase(String hipotese);
    // SELECT *
    // FROM hipotese
    // WHERE hipotese LIKE ?;

    // Buscar hipóteses por descrição
    List<Hipotese> findByDescricaoHipoteseContainingIgnoreCase(String descricao);
    // SELECT *
    // FROM hipotese
    // WHERE descricao_hipotese LIKE ?;

}
