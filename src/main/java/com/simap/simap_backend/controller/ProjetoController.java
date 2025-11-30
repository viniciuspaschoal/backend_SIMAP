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
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping("/filtrar")
    public ResponseEntity<?> filtrarProjetos(@RequestBody FiltroProjetoRequest filtro) {
        try {
            List<ProjetoRelatorioDTO> relatorios = projetoService.filtrarProjetos(filtro);
            return ResponseEntity.ok(relatorios);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("erro", e.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("erro", "Erro interno ao processar a solicitação")
            );
        }
    }
}
