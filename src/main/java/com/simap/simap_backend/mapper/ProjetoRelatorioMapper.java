package com.simap.simap_backend.mapper;

import com.simap.simap_backend.dto.ProjetoAlunoDTO;
import com.simap.simap_backend.dto.ProjetoCabecalhoDTO;
import com.simap.simap_backend.dto.ProjetoRelatorioDTO;

import java.util.*;
import java.util.stream.Collectors;

public final class ProjetoRelatorioMapper {

    private ProjetoRelatorioMapper() {}

    public static List<ProjetoRelatorioDTO> map(List<Map<String, Object>> rows) {

        // ================================
        // 1) Agrupar por projeto
        // ================================
        Map<String, List<Map<String, Object>>> projetosAgrupados =
                rows.stream().collect(Collectors.groupingBy(
                        r -> r.get("cod_projeto").toString()
                ));

        List<ProjetoRelatorioDTO> relatorios = new ArrayList<>();

        for (var entryProjeto : projetosAgrupados.entrySet()) {

            String codProjeto = entryProjeto.getKey();
            List<Map<String, Object>> linhasProjeto = entryProjeto.getValue();

            // ================================
            // 2) Montar cabeçalho do projeto
            // ================================
            ProjetoCabecalhoDTO cabecalho = montarCabecalho(linhasProjeto);

            // ================================
            // 3) Agrupar por aluno
            // ================================
            Map<String, List<Map<String, Object>>> alunosAgrupados =
                    linhasProjeto.stream().collect(Collectors.groupingBy(
                            r -> r.get("cod_aluno").toString()
                    ));

            List<ProjetoAlunoDTO> alunos = new ArrayList<>();

            for (var entryAluno : alunosAgrupados.entrySet()) {

                List<Map<String, Object>> linhasAluno = entryAluno.getValue();
                ProjetoAlunoDTO aluno = montarAluno(linhasAluno);
                alunos.add(aluno);
            }

            // Ordena por número no projeto
            alunos.sort(Comparator.comparing(ProjetoAlunoDTO::numero));

            relatorios.add(new ProjetoRelatorioDTO(cabecalho, alunos));
        }

        return relatorios;
    }


    // ============================================================
    //                      CABEÇALHO
    // ============================================================
    private static ProjetoCabecalhoDTO montarCabecalho(List<Map<String, Object>> linhasProjeto) {

        Map<String, Object> primeira = linhasProjeto.get(0);

        String escola = safe(primeira.get("nome_escola"));
        String professor = safe(primeira.get("nome_professor"));
        String grupoProjeto = safe(primeira.get("grupoprojeto"));
        String periodoProjeto = safe(primeira.get("periodo_projeto"));

        // ano letivo vem da turma
        Integer anoLetivo = tryParseInt(primeira.get("anoletivo_r"));

        // Datas: coletar todos os dias únicos do mês
        List<Integer> datas = linhasProjeto.stream()
                .map(r -> {
                    var d = r.get("data_projeto");
                    return d != null ? Integer.parseInt(d.toString().substring(8, 10)) : null;
                })
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .toList();

        // pegar mês
        String mes = linhasProjeto.stream()
                .map(r -> r.get("data_projeto"))
                .filter(Objects::nonNull)
                .map(d -> d.toString().substring(5, 7)) // "04"
                .findFirst()
                .orElse(null);

        return new ProjetoCabecalhoDTO(
                escola,
                professor,
                anoLetivo,
                mes,
                periodoProjeto,
                grupoProjeto,
                datas,
                periodoProjeto  // turno = mesmo valor do campo "periodo_projeto"
        );
    }


    // ============================================================
    //                      ALUNOS
    // ============================================================
    private static ProjetoAlunoDTO montarAluno(List<Map<String, Object>> linhasAluno) {

        Map<String, Object> primeira = linhasAluno.get(0);

        Integer numero = tryParseInt(primeira.get("num_projeto")); // número no projeto
        String nome = safe(primeira.get("nome_aluno"));

        String ano = safe(primeira.get("serie"));   // exemplo: "5"
        String turma = safe(primeira.get("turma")); // exemplo: "A"
        String turno = safe(primeira.get("turno_matricula"));

        // Agrupar frequências por data
        Map<String, String> frequencias = new TreeMap<>();

        for (Map<String, Object> row : linhasAluno) {

            if (row.get("data_projeto") != null) {
                String data = row.get("data_projeto").toString().substring(8, 10);
                String freq = safe(row.get("frequencia"));
                if (freq != null && !freq.isBlank()) {
                    frequencias.put(data, freq);
                }
            }
        }

        String obs = safe(primeira.get("observacao"));

        return new ProjetoAlunoDTO(
                numero,
                nome,
                ano,
                turma,
                turno,
                frequencias,
                obs
        );
    }


    // ============================================================
    //                 FUNÇÕES AUXILIARES
    // ============================================================
    private static String safe(Object o) {
        return o != null ? o.toString() : null;
    }

    private static Integer tryParseInt(Object o) {
        try {
            return o != null ? Integer.parseInt(o.toString()) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
