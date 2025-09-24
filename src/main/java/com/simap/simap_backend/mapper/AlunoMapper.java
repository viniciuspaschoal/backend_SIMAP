package com.simap.simap_backend.mapper;


import com.simap.simap_backend.dto.AlunoDTO;
import com.simap.simap_backend.dto.BimestreDTO;
import com.simap.simap_backend.dto.DiagnosticosDTO;
import com.simap.simap_backend.dto.MapaAlfabetizacaoDTO;
import com.simap.simap_backend.model.Aluno;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mapper responsável por montar o payload completo de "Aluno individual"
 * consumido pelo front.
 *
 * Observações:
 * - Por padrão, dataNascimento vai em ISO (yyyy-MM-dd).
 *   Se quiser no formato brasileiro, troque DATE_FMT para:
 *   DateTimeFormatter.ofPattern("dd/MM/yyyy")
 */
public final class AlunoMapper {

    private AlunoMapper() {}

    // Formato de data: ISO por padrão. Troque para BR se preferir.
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;
    // private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Monta o DTO completo do aluno para a página individual.
     */
    public static AlunoDTO toDTO(
            Aluno aluno,
            String escola,
            String serie,
            String turma,
            String turno,
            String declaracao,
            String mapaInicial,
            BimestreDTO b1,
            BimestreDTO b2,
            BimestreDTO b3,
            BimestreDTO b4,
            Integer numeroChamada
    ) {
        String dataNasc = aluno.getDataNascimento() != null
                ? aluno.getDataNascimento().format(DATE_FMT)
                : null;

        return new AlunoDTO(
                aluno.getCodAluno(),
//                numeroChamada,
                aluno.getNomeAluno(),
                aluno.getRa(),
                nz(escola),
                nz(serie),
                nz(turma),
                nz(turno),
                nz(declaracao),
                dataNasc != null ? dataNasc : "-",
                new MapaAlfabetizacaoDTO(nz(mapaInicial)),
                new DiagnosticosDTO(
                        nz(b1), nz(b2), nz(b3), nz(b4)
                )
        );
    }

    /** Atalho para criar um BimestreDTO já normalizado (aceita nulls). */
    public static BimestreDTO bimestre(String alfabetizacao, String frequencia, String projeto) {
        return new BimestreDTO(nz(alfabetizacao), nz(frequencia), nz(projeto));
    }

    /** Garante que um BimestreDTO nunca seja null e normaliza seus campos. */
    public static BimestreDTO nz(BimestreDTO b) {
        if (b == null) return new BimestreDTO("-", "-", "-");
        return new BimestreDTO(
                nz(b.alfabetizacao()),
                nz(b.frequencia()),
                nz(b.projeto())
        );
    }

    /** Normaliza string null/blank para "-" (evita quebrar layout no front). */
    public static String nz(String s) {
        return (s == null || s.isBlank()) ? "-" : s.trim();
    }

    /**
     * Junta códigos de projetos em "ALF/MAT1". Se a lista for vazia/null, retorna "-".
     * Remove duplicados e espaços extras.
     */
    public static String joinProjetos(List<String> codigos) {
        if (codigos == null || codigos.isEmpty()) return "-";
        String joined = codigos.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(x -> !x.isBlank())
                .distinct()
                .collect(Collectors.joining("/"));
        return nz(joined);
    }
}
