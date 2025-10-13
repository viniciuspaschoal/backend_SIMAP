package com.simap.simap_backend.service;

import com.simap.simap_backend.dto.FiltroAlunosRequest;
import com.simap.simap_backend.dto.ResultadoFiltrosDTO;
import com.simap.simap_backend.mapper.ResultadoFiltrosMapper;
import com.simap.simap_backend.model.Aluno;
import com.simap.simap_backend.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FiltroAlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    //Construtor
    public FiltroAlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public List<ResultadoFiltrosDTO> buscarAlunosPorFiltro(FiltroAlunosRequest filtros){
        // Chama o metodo do repository dinâmico
        List<Map<String, Object>> resultado = alunoRepository.filtrarAlunosDinamico(filtros);

        // Converte cada linha (Map) em DTO
        return resultado.stream()
                .map(ResultadoFiltrosMapper::fromRow)
                .collect(Collectors.toList());

    }
}
