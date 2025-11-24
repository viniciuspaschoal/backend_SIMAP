package com.simap.simap_backend.dto;

import java.util.List;

public record ProjetoCabecalhoDTO(
        String escola,
        String professor,
        Integer ano,
        String mes,
        String horarioProjeto,
        String grupoProjeto,
        List<Integer> datas,
        String turno
) {}


