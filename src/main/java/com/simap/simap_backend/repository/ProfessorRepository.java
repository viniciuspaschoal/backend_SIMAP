package com.simap.simap_backend.repository;

import com.simap.simap_backend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {

    //Buscar professor por nome exato
    List<Professor> findByNomeProfessor(String Nome);

    //Buscar professor por parte do nome sem diferenciar maiúscula e minúscula (ex: "joão" traria "João", "JOÃO", etc.)
    List<Professor> findByNomeProfessorContainingIgnoreCase(String Nome);

    //Buscar professor pelo email (único, então deve retornar um só)
    Professor findByEmailProfessor(String Email);

    //Buscar professores que lecionam em uma turma específica, usando o relacionamento com ProfessorTurma
    Professor findByProfessorTurmas_Turma_CodTurma(String CodTurma);
    // SELECT p.*
    // FROM professor p
    // JOIN professor_turma pt ON pt.cod_professor = p.cod_professor
    // JOIN turma t ON pt.cod_turma = t.cod_turma
    // WHERE t.cod_turma = '0001';

    //Buscar professor por código (ID)
    Professor findByCocProfessor(String CodProfessor);
}
