package com.simap.simap_backend.repository;

import com.simap.simap_backend.dto.FiltroAlunosRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AlunoRepositoryImpl implements AlunoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Map<String, Object>> filtrarAlunosDinamico(FiltroAlunosRequest filtro) {
        StringBuilder sql = new StringBuilder(
                "SELECT " +
                        "  a.cod_aluno, " +
                        "  a.nome_aluno, " +
                        "  a.ra, " +
                        "  e.nome_escola, " +
                        "  t.anoletivo_r, " +
                        "  t.serie, " +
                        "  t.turma, " +
                        "  at.nro_chamada, " +

                        "  STRING_AGG(DISTINCT CASE WHEN d.bimestre_diagnostico = '1' THEN h.hipotese END, ', ') AS b1_hipoteses, " +
                        "  STRING_AGG(DISTINCT CASE WHEN d.bimestre_diagnostico = '2' THEN h.hipotese END, ', ') AS b2_hipoteses, " +
                        "  STRING_AGG(DISTINCT CASE WHEN d.bimestre_diagnostico = '3' THEN h.hipotese END, ', ') AS b3_hipoteses, " +
                        "  STRING_AGG(DISTINCT CASE WHEN d.bimestre_diagnostico = '4' THEN h.hipotese END, ', ') AS b4_hipoteses, " +

                        "  fr1.frequencia_r AS faltas_b1, " +
                        "  fr2.frequencia_r AS faltas_b2, " +
                        "  fr3.frequencia_r AS faltas_b3, " +
                        "  fr4.frequencia_r AS faltas_b4, " +

                        "  gp1.grupoprojeto AS grupo_b1, " +
                        "  gp2.grupoprojeto AS grupo_b2, " +
                        "  gp3.grupoprojeto AS grupo_b3, " +
                        "  gp4.grupoprojeto AS grupo_b4 " +
                        "FROM aluno a " +
                        "LEFT JOIN aluno_turma at ON at.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN turma t ON t.cod_turma = at.cod_turma " +
                        "LEFT JOIN escola e ON e.cod_escola = t.cod_escola " +
                        "LEFT JOIN resultado_diagnostico rd ON rd.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN diagnostico d ON d.cod_diagnostico = rd.cod_diagnostico AND d.cod_turma = t.cod_turma " +
                        "LEFT JOIN hipotese h ON h.cod_hipotese = rd.cod_hipotese " +
                        // BIMESTRE 1
                        "LEFT JOIN frequencia_regular fr1 ON fr1.cod_aluno = a.cod_aluno AND fr1.cod_turma = t.cod_turma AND fr1.bimestre_r = '1' " +
                        "LEFT JOIN aluno_projeto ap1 ON ap1.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN projeto_recomposicao pr1 ON pr1.cod_projeto = ap1.cod_projeto " +
                        "LEFT JOIN grupo_projeto gp1 ON gp1.cod_grupoprojeto = pr1.cod_grupoprojeto " +

// BIMESTRE 2
                        "LEFT JOIN frequencia_regular fr2 ON fr2.cod_aluno = a.cod_aluno AND fr2.cod_turma = t.cod_turma AND fr2.bimestre_r = '2' " +
                        "LEFT JOIN aluno_projeto ap2 ON ap2.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN projeto_recomposicao pr2 ON pr2.cod_projeto = ap2.cod_projeto " +
                        "LEFT JOIN grupo_projeto gp2 ON gp2.cod_grupoprojeto = pr2.cod_grupoprojeto " +

// BIMESTRE 3
                        "LEFT JOIN frequencia_regular fr3 ON fr3.cod_aluno = a.cod_aluno AND fr3.cod_turma = t.cod_turma AND fr3.bimestre_r = '3' " +
                        "LEFT JOIN aluno_projeto ap3 ON ap3.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN projeto_recomposicao pr3 ON pr3.cod_projeto = ap3.cod_projeto " +
                        "LEFT JOIN grupo_projeto gp3 ON gp3.cod_grupoprojeto = pr3.cod_grupoprojeto " +

// BIMESTRE 4
                        "LEFT JOIN frequencia_regular fr4 ON fr4.cod_aluno = a.cod_aluno AND fr4.cod_turma = t.cod_turma AND fr4.bimestre_r = '4' " +
                        "LEFT JOIN aluno_projeto ap4 ON ap4.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN projeto_recomposicao pr4 ON pr4.cod_projeto = ap4.cod_projeto " +
                        "LEFT JOIN grupo_projeto gp4 ON gp4.cod_grupoprojeto = pr4.cod_grupoprojeto " +
                        "WHERE 1=1 "
        );

        // === FILTROS SIMPLES ===
        if (filtro.escolas() != null && !filtro.escolas().isEmpty()) {
            sql.append(" AND UPPER(TRIM(e.nome_escola)) IN :escolas");
        }
        if (filtro.series() != null && !filtro.series().isEmpty()) {
            sql.append(" AND TRIM(t.serie) IN :series");
        }
        if (filtro.turmas() != null && !filtro.turmas().isEmpty()) {
            sql.append(" AND TRIM(t.turma) IN :turmas");
        }

        if (filtro.anoLetivo() != null && !filtro.anoLetivo().isEmpty()) {
            sql.append(" AND t.anoletivo_r IN :anos");
        }

        // === FILTROS DINÂMICOS POR BIMESTRE ===
        if (filtro.diagnosticos() != null) {
            for (Map.Entry<String, List<String>> eDiag : filtro.diagnosticos().entrySet()) {
                String bimestre = eDiag.getKey();
                List<String> hipoteses = eDiag.getValue();

                if (hipoteses != null && !hipoteses.isEmpty()) {
                    sql.append("""
                                AND EXISTS (
                                    SELECT 1
                                    FROM resultado_diagnostico rd_sub
                                    JOIN diagnostico d_sub ON d_sub.cod_diagnostico = rd_sub.cod_diagnostico
                                    JOIN hipotese h_sub ON h_sub.cod_hipotese = rd_sub.cod_hipotese
                                    WHERE rd_sub.cod_aluno = a.cod_aluno
                                      AND d_sub.cod_turma = t.cod_turma
                                      AND d_sub.bimestre_diagnostico = :bim_%s
                                      AND TRIM(UPPER(h_sub.hipotese)) IN :hip_%s
                                )
                            """.formatted(bimestre, bimestre));
                }
            }
        }

        sql.append(
                        " GROUP BY " +
                                "  a.cod_aluno, a.nome_aluno, a.ra, e.nome_escola, t.anoletivo_r, t.serie, t.turma, at.nro_chamada, " +
                                "  fr1.frequencia_r, fr2.frequencia_r, fr3.frequencia_r, fr4.frequencia_r, " +
                                "  gp1.grupoprojeto, gp2.grupoprojeto, gp3.grupoprojeto, gp4.grupoprojeto "
                )
                .append(" ORDER BY e.nome_escola, t.serie, t.turma, a.nome_aluno");

        Query query = em.createNativeQuery(sql.toString());

        // === PARAMETROS NORMALIZADOS ===
        if (filtro.escolas() != null && !filtro.escolas().isEmpty()) {
            query.setParameter("escolas",
                    filtro.escolas().stream()
                            .map(String::trim)
                            .map(String::toUpperCase)
                            .toList());
        }
        if (filtro.series() != null && !filtro.series().isEmpty()) {
            query.setParameter("series",
                    filtro.series().stream().map(String::trim).toList());
        }
        if (filtro.turmas() != null && !filtro.turmas().isEmpty()) {
            query.setParameter("turmas",
                    filtro.turmas().stream().map(String::trim).toList());
        }

        if (filtro.anoLetivo() != null && !filtro.anoLetivo().isEmpty()) {
            query.setParameter("anos",
                    filtro.anoLetivo());
        }

        if (filtro.diagnosticos() != null) {
            for (Map.Entry<String, List<String>> eDiag : filtro.diagnosticos().entrySet()) {
                String bimestre = eDiag.getKey();
                List<String> hipoteses = eDiag.getValue();
                if (hipoteses != null && !hipoteses.isEmpty()) {
                    query.setParameter("bim_" + bimestre, bimestre);
                    query.setParameter("hip_" + bimestre,
                            hipoteses.stream()
                                    .map(String::trim)
                                    .map(String::toUpperCase)
                                    .toList());
                }
            }
        }

        // converte para lista de Map<String,Object>
        query.unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        return query.getResultList();
    }
}
