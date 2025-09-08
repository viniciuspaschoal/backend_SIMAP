package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.Aluno;
import com.simap.simap_backend.model.AlunoTurma;
import com.simap.simap_backend.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, String> {

    //Buscar por nome exato
    List<Aluno> findByNomeAluno(String nome);

    //Buscar aluno por parte do nome, sem diferenciar maiúscula e minúscula
    List<Aluno> findByNomeAlunoContainingIgnoreCase(String nome);

    //Buscar todos os alunos que tem em uma determinada turma
    List<Aluno> findByAlunoTurmas_Turma_CodTurma(String codTurma);

    //Busca aluno por RA - deve ser único
    Aluno findByRa(String ra);

    //Busca alunos por declaração
    List<Aluno> findByDeclaracao_CodDeclaracao(String codDeclaracao);
}
