package com.simap.simap_backend.dto;

import java.util.Map;

public record ProjetoAlunoDTO(
        Integer numero,                 // Nº na lista do projeto
        String nome,                    // Nome completo
        String ano,                     // Apenas o ano, ex: "5º" ou "4º"
        String turma,                   // Apenas a turma, ex: "A" ou "B"
        String turnoMatricula,          // Ex: "Manhã" / "Tarde"
        Map<String, String> frequenciaPorData, // {"06": "C", "13": "F", ...}
        String observacao               // Texto livre
) {}