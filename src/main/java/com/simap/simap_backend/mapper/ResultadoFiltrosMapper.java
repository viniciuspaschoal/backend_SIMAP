package com.simap.simap_backend.mapper;

import com.simap.simap_backend.dto.ResultadoFiltrosDTO;

import java.util.Map;

public final class ResultadoFiltrosMapper{

    private ResultadoFiltrosMapper(){}

    public static ResultadoFiltrosDTO fromRow(Map<String, Object> row){
        return new ResultadoFiltrosDTO(
                safeToString(row.get("cod_aluno")),
                safeToString(row.get("nome_aluno")),
                safeToString(row.get("ra")),
                safeToString(row.get("nome_escola")),
                safeToString(row.get("serie")),
                safeToString(row.get("turma")),
                safeToString(row.get("b1_hipoteses")),
                safeToString(row.get("b2_hipoteses")),
                safeToString(row.get("b3_hipoteses")),
                safeToString(row.get("b4_hipoteses"))
        );
    }

    private static String safeToString(Object value){
        return value != null ? value.toString() : null;
    }
}