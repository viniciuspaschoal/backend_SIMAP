package com.simap.simap_backend.service;

import com.simap.simap_backend.dto.FiltroProjetoRequest;
import com.simap.simap_backend.dto.ProjetoRelatorioDTO;

import java.util.List;
import java.util.Map;

public interface ProjetoService {

    List<ProjetoRelatorioDTO> filtrarProjetos(FiltroProjetoRequest filtro);
}
