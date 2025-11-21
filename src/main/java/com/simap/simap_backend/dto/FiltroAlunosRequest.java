package com.simap.simap_backend.dto;

import java.util.List;
import java.util.Map;

public record FiltroAlunosRequest(
        List<Integer> anoLetivo,
        List<String> escolas,
        List<String> series,
        List<String> turmas,
        Map<String, List<String>> diagnosticos
) {}
