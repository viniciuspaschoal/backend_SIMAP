package com.simap.simap_backend.dto;

import java.time.LocalDate;

/**
 * DTO para a tela de detalhe do aluno.
 * Nesta primeira versão, apenas os campos básicos da entidade Aluno.
 * Esse é o JSON que será entregue
 */
public record AlunoDTO(
        String codAluno,
//        Integer numeroChamada,
        String nomeAluno,
        String ra,
        String escola,
        String serie,
        String turma,
        String turno,
        String declaracao,
        String dataNascimento,
        MapaAlfabetizacaoDTO mapaAlfabetizacao,
        DiagnosticosDTO diagnosticos
) {}
