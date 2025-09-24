package com.simap.simap_backend.dto;

public record DiagnosticosDTO(
        BimestreDTO primeiroBimestre,
        BimestreDTO segundoBimestre,
        BimestreDTO terceiroBimestre,
        BimestreDTO quartoBimestre
) {}
