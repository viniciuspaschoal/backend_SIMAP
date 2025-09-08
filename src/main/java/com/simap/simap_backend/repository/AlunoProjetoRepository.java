package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.AlunoProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoProjetoRepository extends JpaRepository<AlunoProjeto, String> {

    // Buscar todos os projetos de um aluno específico
    List<AlunoProjeto> findById_CodAluno(String codAluno);

    //SELECT *
    //FROM aluno_projeto
    //WHERE cod_aluno = ?

    // Buscar todos os alunos de um projeto específico
    List<AlunoProjeto> findById_CodProjeto(String codProjeto);

    //SELECT *
    //FROM aluno_projeto
    //WHERE cod_projeto = ?

    // Buscar aluno específico em projeto específico
    AlunoProjeto findById_CodAlunoAndId_CodProjeto(String codAluno, String codProjeto);

    //SELECT *
    //FROM aluno_projeto
    // WHERE cod_aluno = ? AND cod_projeto = ?
}
