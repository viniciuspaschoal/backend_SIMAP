package com.simap.simap_backend.dto;

import java.util.List;

public record FiltroProjetoRequest(
        List<String> escolas,          // Nome da escola do projeto (origem do projeto)
        List<String> grupoProjeto,     // ALF, MAT2, LP2, MAT1/LP2...
        List<String> professores,      // Professor responsável pelo projeto
        List<Integer> anoLetivo,       // Ano letivo do projeto (2024, 2025...)
        List<Integer> meses,           // Meses do projeto (1..12)
        List<String> turnosProjeto
) {}
