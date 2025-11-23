package com.simap.simap_backend.dto;

import java.util.Map;

public record ProjetoAlunoDTO(
        Integer numero,
        String nome,
        String anoTurma,
        String serie,
        String turnoMatricula,
        Map<String, String> frequenciaPorData,
        String observacao
) {
}
