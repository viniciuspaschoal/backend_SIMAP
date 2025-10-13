package com.simap.simap_backend.controller;

import com.simap.simap_backend.dto.AlunoDTO;
import com.simap.simap_backend.dto.FiltroAlunosRequest;
import com.simap.simap_backend.dto.ResultadoFiltrosDTO;
import com.simap.simap_backend.service.AlunoService;
import com.simap.simap_backend.service.FiltroAlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Combina @Controller + @ResponseBody, então o retorno dos métodos vira JSON automaticamente.
@RestController
@RequestMapping("api/aluno")
public class AlunoController {

    private final AlunoService alunoService;
    private final FiltroAlunoService filtroAlunoService;

    public AlunoController(AlunoService alunoService, FiltroAlunoService filtroAlunoService){
        this.alunoService = alunoService;
        this.filtroAlunoService = filtroAlunoService;
    }

    // GET /api/aluno/ra/{ra}
    @GetMapping("/ra/{ra}")
    public ResponseEntity<AlunoDTO> buscarPorRA(@PathVariable String ra){
//        Aluno aluno = alunoService.obterPorRA(ra);      //busca no banco
//        AlunoDTO dto = AlunoMapper.toDTO(aluno);  //converte para DTO de saída
//        return ResponseEntity.ok(dto);                  //retorna DTO + JSON
        return ResponseEntity.ok(alunoService.obterCompletoporRA(ra));
    }

    // Metodo POST para receber os filtros
    @PostMapping("/filtro")
    public ResponseEntity<List<ResultadoFiltrosDTO>> receberFiltragem(@RequestBody FiltroAlunosRequest filtros) {
        // Log opcional (só pra acompanhar no console)
        System.out.println("Filtros Recebidos: " + filtros);

        //Chama o Service, que faz a busca no banco e monta os DTOs
        List<ResultadoFiltrosDTO> resultado = filtroAlunoService.buscarAlunosPorFiltro(filtros);

        // Retorna uma confirmação de que os filtros foram recebidos com sucesso
        return ResponseEntity.ok(resultado);
    }


}
