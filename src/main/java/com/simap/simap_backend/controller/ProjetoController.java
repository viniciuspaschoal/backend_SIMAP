package com.simap.simap_backend.controller;

import com.simap.simap_backend.dto.FiltroAlunosRequest;
import com.simap.simap_backend.dto.FiltroProjetoRequest;
import com.simap.simap_backend.dto.ProjetoRelatorioDTO;
import com.simap.simap_backend.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projeto-recomposicao")
public class ProjetoController {

    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService){
        this.projetoService = projetoService;
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<ProjetoRelatorioDTO>> filtrarProjetos(@RequestBody FiltroProjetoRequest filtro){
        List<ProjetoRelatorioDTO> relatorios = projetoService.filtrarProjetos(filtro);

        return ResponseEntity.ok(relatorios);
    }
}
