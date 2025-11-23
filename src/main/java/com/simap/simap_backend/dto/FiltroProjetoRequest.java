package com.simap.simap_backend.dto;

import java.util.List;

public record FiltroProjetoRequest(
        List<String> escolas,               // Filtrar por nome da escola
        List<String> grupoProjeto,          // Filtrar por grupo do projeto (ALF, LP2, MAT2...)
        List<String> professores,           // Filtrar por nome do professor(a)
        List<Integer> anoLetivo,            // Ano letivo (2024, 2025…)
        List<Integer> meses,                // Meses (1 a 12)
        List<Integer> series,               // 1º, 2º, 3º...
        List<Integer> turmas                // A, B, C...
) {}
