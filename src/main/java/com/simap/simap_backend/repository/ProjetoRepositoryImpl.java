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
                        " prof.nome_professor, " +

                        // Aluno
                        " a.cod_aluno, " +
                        " a.nome_aluno, " +
                        " a.observacao AS observacao, " +

                        // Turma
                        " t.serie, " +
                        " t.turma, " +
                        " t.periodo_regular AS turno_matricula, " +
                        " t.anoletivo_r, " +

                        // Número do aluno no projeto
                        " ap.nro_chamada_projeto AS num_projeto, " +

                        // Frequência
                        " fp.frequencia_p AS frequencia " +

                        "FROM projeto_recomposicao pr " +

                        "JOIN professor prof ON prof.cod_professor = pr.cod_professor " +
                        "JOIN grupo_projeto gp ON gp.cod_grupoprojeto = pr.cod_grupoprojeto " +
                        "JOIN data_projeto dp ON dp.cod_projeto = pr.cod_projeto " +

                        "JOIN frequencia_projeto fp ON fp.cod_dataprojeto = dp.cod_dataprojeto " +

                        "JOIN aluno_projeto ap ON ap.cod_projeto = pr.cod_projeto AND ap.cod_aluno = fp.cod_aluno " +
                        "JOIN aluno a ON a.cod_aluno = ap.cod_aluno " +

                        "JOIN aluno_turma at ON at.cod_aluno = a.cod_aluno " +
                        "JOIN turma t ON t.cod_turma = at.cod_turma " +

                        "JOIN escola es ON es.cod_escola = t.cod_escola " +

                        "WHERE 1=1 "
        );

        // ====================================
        //               FILTROS
        // ====================================

        if (filtro.escolas() != null && !filtro.escolas().isEmpty()) {
            sql.append(" AND UPPER(TRIM(es.nome_escola)) IN :escolas");
        }

        if (filtro.grupoProjeto() != null && !filtro.grupoProjeto().isEmpty()) {
            sql.append(" AND UPPER(TRIM(gp.grupoprojeto)) IN :grupoProjeto");
        }

        if (filtro.professores() != null && !filtro.professores().isEmpty()) {
            sql.append(" AND UPPER(TRIM(prof.nome_professor)) IN :professores");
        }

        if (filtro.anoLetivo() != null && !filtro.anoLetivo().isEmpty()) {
            sql.append(" AND t.anoletivo_r IN :anoLetivo");
        }

        if (filtro.meses() != null && !filtro.meses().isEmpty()) {
            sql.append(" AND EXTRACT(MONTH FROM dp.data_projeto) IN :meses");
        }

        if (filtro.turnosProjeto() != null && !filtro.turnosProjeto().isEmpty()) {
            sql.append(" AND UPPER(TRIM(pr.periodo_projeto)) IN :turnosProjeto");
        }

        // ====================================
        //              ORDER BY
        // ====================================
        sql.append(
                " ORDER BY es.nome_escola, gp.grupoprojeto, prof.nome_professor, " +
                        " pr.cod_projeto, a.nome_aluno, dp.data_projeto"
        );

        // ====================================
        //              QUERY
        // ====================================
        Query query = em.createNativeQuery(sql.toString());

        // ====================================
        //      PARÂMETROS DINÂMICOS
        // ====================================
        if (filtro.escolas() != null && !filtro.escolas().isEmpty()) {
            query.setParameter("escolas",
                    filtro.escolas().stream()
                            .map(String::trim).map(String::toUpperCase).toList());
        }

        if (filtro.grupoProjeto() != null && !filtro.grupoProjeto().isEmpty()) {
            query.setParameter("grupoProjeto",
                    filtro.grupoProjeto().stream()
                            .map(String::trim).map(String::toUpperCase).toList());
        }

        if (filtro.professores() != null && !filtro.professores().isEmpty()) {
            query.setParameter("professores",
                    filtro.professores().stream()
                            .map(String::trim).map(String::toUpperCase).toList());
        }

        if (filtro.anoLetivo() != null && !filtro.anoLetivo().isEmpty()) {
            query.setParameter("anoLetivo", filtro.anoLetivo());
        }

        if (filtro.meses() != null && !filtro.meses().isEmpty()) {
            query.setParameter("meses", filtro.meses());
        }

        if (filtro.turnosProjeto() != null && !filtro.turnosProjeto().isEmpty()) {
            query.setParameter("turnosProjeto",
                    filtro.turnosProjeto().stream()
                            .map(String::trim).map(String::toUpperCase).toList());
        }

        // ====================================
        //            RESULTADO
        // ====================================
        query.unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        return query.getResultList();
    }
}
