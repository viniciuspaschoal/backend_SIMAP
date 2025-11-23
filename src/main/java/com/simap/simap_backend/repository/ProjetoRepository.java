package com.simap.simap_backend.repository;

import com.simap.simap_backend.dto.FiltroProjetoRequest;

import java.util.List;
import java.util.Map;

public interface ProjetoRepository {
    /**
     * Executa busca dinâmica dos relatórios de projeto de recomposição
     * baseado nos filtros enviados pelo frontend.
     *
     * @param filtro Filtros opcionais: escola, grupo, professor, ano letivo, mês, série, turma.
     * @return Lista de registros em formato Map<String, Object>.
     */

    List<Map<String, Object>> filtrarProjetos(FiltroProjetoRequest filtro);
}
