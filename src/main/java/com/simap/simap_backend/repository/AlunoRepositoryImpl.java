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
                        "  STRING_AGG(DISTINCT CASE WHEN d.bimestre_diagnostico = '4' THEN h.hipotese END, ', ') AS b4_hipoteses " +
                        "FROM aluno a " +
                        "LEFT JOIN aluno_turma at ON at.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN turma t ON t.cod_turma = at.cod_turma " +
                        "LEFT JOIN escola e ON e.cod_escola = t.cod_escola " +
                        "LEFT JOIN resultado_diagnostico rd ON rd.cod_aluno = a.cod_aluno " +
                        "LEFT JOIN diagnostico d ON d.cod_diagnostico = rd.cod_diagnostico AND d.cod_turma = t.cod_turma " +
                        "LEFT JOIN hipotese h ON h.cod_hipotese = rd.cod_hipotese " +
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

        sql.append(" GROUP BY a.cod_aluno, a.nome_aluno, a.ra, e.nome_escola, t.anoletivo_r, t.serie, t.turma, at.nro_chamada ")
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

        if (filtro.anoLetivo() != null && !filtro.anoLetivo().isEmpty()){
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
