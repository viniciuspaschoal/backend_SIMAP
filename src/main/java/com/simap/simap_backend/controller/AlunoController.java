package com.simap.simap_backend.controller;

import com.simap.simap_backend.dto.AlunoDTO;
import com.simap.simap_backend.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Combina @Controller + @ResponseBody, então o retorno dos métodos vira JSON automaticamente.
@RestController
@RequestMapping("api/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    // GET /api/aluno/ra/{ra}
    @GetMapping("/ra/{ra}")
    public ResponseEntity<AlunoDTO> buscarPorRA(@PathVariable String ra){
//        Aluno aluno = alunoService.obterPorRA(ra);      //busca no banco
//        AlunoDTO dto = AlunoMapper.toDTO(aluno);  //converte para DTO de saída
//        return ResponseEntity.ok(dto);                  //retorna DTO + JSON
        return ResponseEntity.ok(alunoService.obterCompletoporRA(ra));
    }
}
