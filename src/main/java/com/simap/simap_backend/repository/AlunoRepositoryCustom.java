package com.simap.simap_backend.repository;

import com.simap.simap_backend.dto.FiltroAlunosRequest;
import com.simap.simap_backend.dto.ResultadoFiltrosDTO;

import java.util.List;
import java.util.Map;

public interface AlunoRepositoryCustom {
    List<Map<String, Object>> filtrarAlunosDinamico(FiltroAlunosRequest filtro);
}
