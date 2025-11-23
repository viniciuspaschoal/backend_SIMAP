package com.simap.simap_backend.repository;

import com.simap.simap_backend.dto.FiltroProjetoRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProjetoRepositoryImpl implements ProjetoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Map<String, Object>> filtrarProjetos(FiltroProjetoRequest filtro) {

        StringBuilder sql = new StringBuilder(
                "SELECT " +
                        " es.nome_escola, " +
                        " gp.grupoprojeto, " +
                        " pr.periodo_projeto, " +
                        " pr.cod_projeto, " +
                        " dp.data_projeto, " +
                        " prof.nome_professor " +
                        "FROM projeto_recomposicao pr " +
                        "JOIN professor prof ON prof.cod_professor = pr.cod_professor " +
                        "JOIN grupo_projeto gp ON gp.cod_grupoprojeto = pr.cod_grupoprojeto " +
                        "JOIN data_projeto dp ON dp.cod_projeto = pr.cod_projeto " +
                        "JOIN aluno_projeto ap ON ap.cod_projeto = pr.cod_projeto " +
                        "JOIN aluno a ON a.cod_aluno = ap.cod_aluno " +
                        "JOIN aluno_turma at ON at.cod_aluno = a.cod_aluno " +
                        "JOIN turma t ON t.cod_turma = at.cod_turma " +
                        "JOIN escola es ON es.cod_escola = t.cod_escola " +
                        "WHERE 1=1 "
        );

        //TODO
        //inserir filtros mais tarde

        Query query = em.createNativeQuery(sql.toString());

        query.unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        return query.getResultList();
    }
}
