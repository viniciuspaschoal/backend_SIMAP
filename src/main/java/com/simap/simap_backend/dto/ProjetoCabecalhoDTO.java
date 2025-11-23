package com.simap.simap_backend.dto;

public record ProjetoCabecalhoDTO(
        String escola,
        String professor,
        Integer ano,
        String mes,
        String horarioProjeto,
        String grupoPorjeto,
        String turno
) {}
