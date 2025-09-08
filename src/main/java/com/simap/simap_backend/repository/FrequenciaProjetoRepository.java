package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.FrequenciaProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrequenciaProjetoRepository extends JpaRepository<FrequenciaProjeto, String> {

    // Buscar todas as frequências de um aluno específico
    List<FrequenciaProjeto> findById_CodAluno(String codAluno);

    //SELECT *
    //FROM frequencia_projeto
    //WHERE cod_dataprojeto = ?

    // Buscar todas frequências de um projeto específico
    List<FrequenciaProjeto> findById_CodDataProjeto(String codDataProjeto);

    //SELECT *
    //FROM frequencia_projeto
    //WHERE codDataProjeto = ?

    // Buscar frequência específica de um aluno em um projeto
    FrequenciaProjeto findById_CodAlunoAndId_CodDataProjeto(String codAluno, String codDataProjeto);
    //SELECT *
    //FROM frequencia_projeto
    //WHERE cod_aluno = ? AND cod_dataprojeto = ?;

    // Buscar todas as frequências de uma lista de projetos
    List<FrequenciaProjeto> findById_CodDataProjetoIn(List<String> codDataProjetos);
    //SELECT *
    //FROM frequencia_projeto
    //WHERE cod_dataprojeto IN (?, ?, ...);
}
