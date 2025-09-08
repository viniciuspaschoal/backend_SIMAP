package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.DataProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DataProjetoRepository extends JpaRepository<DataProjeto, String> {

    // Buscar a data do projeto específico
    List<DataProjeto> findByProjeto_CodProjeto(String codProjeto);

    //SELECT *
    //FROM data_pojeto
    //WHERE cod_projeto = ?

    // Buscar projetos relacionados a data específica
    List<DataProjeto> findByDataProjeto(LocalDate dataProjeto);

    //SELECT *
    //FROM data_projeto
    //WHERE data_projeto = ?

    // Buscar projetos em um período de data
    List<DataProjeto> findByDataProjetoBetween(LocalDate startDate, LocalDate endDate);

    //SELECT *
    //FROM data_projeto
    //WHERE data_projeto BETWEEN ? AND ?;

    @Query("SELECT dp FROM DataProjeto dp JOIN dp.projeto pr WHERE pr.codGrupoprojeto = :codGrupo AND dp.dataProjeto BETWEEN :startDate AND :endDate")
    List<DataProjeto> findByGrupoProjetoAndDateRange(@Param("codGrupo") String codGrupo,
                                                     @Param("startDate")LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate);

    //SELECT dp.*
    //FROM data_projeto dp
    //JOIN projeto_recomposicao pr ON dp.cod_projeto = pr.cod_projeto
    //WHERE pr.cod_grupoprojeto = ?
    //AND dp.data_projeto BETWEEN ? AND ?;
}
