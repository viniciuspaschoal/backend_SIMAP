package com.simap.simap_backend.dto;

/**
 * DTO resumido para listagem de alunos nos filtros.
 * Contém apenas informações básicas de identificação e vínculo escolar.
 */
public record ResultadoFiltrosDTO(
        String codAluno,
        String nomeAluno,
        String ra,
        String escola,
        String anoLetivo,
        String serie,
        String turma,
        String nChamada,
        String hipoteseBimestre1,
        String hipoteseBimestre2,
        String hipoteseBimestre3,
        String hipoteseBimestre4
) {}