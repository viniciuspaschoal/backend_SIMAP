package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, String> {

    // ----------------------------
    // Regras de negócio possíveis:
    // ----------------------------

    // Buscar escola pelo nome exato
    Escola findByNomeEscola(String nome);

    // Buscar escolas contendo parte do nome (ignora maiúscula/minúscula)
    List<Escola> findByNomeEscolaContainingIgnoreCase(String nome);

    // Buscar escolas por cidade
    List<Escola> findByEndCidade(String cidade);

    // Buscar escolas por estado (ex: "SP", "RJ")
    List<Escola> findByEndEstado(String estado);

    // Buscar escola pelo CEP
    Escola findByEndCep(String cep);

    // Verificar se existe escola pelo nome
    boolean existsByNomeEscola(String nome);

}
