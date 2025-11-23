package com.simap.simap_backend.dto;

import java.util.List;

public record ProjetoRelatorioDTO (
        ProjetoCabecalhoDTO cabecalho,
        List<ProjetoAlunoDTO> alunos
){}
