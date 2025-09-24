package com.simap.simap_backend.service;

import com.simap.simap_backend.dto.AlunoDTO;
import com.simap.simap_backend.dto.BimestreDTO;
import com.simap.simap_backend.mapper.AlunoMapper;
import com.simap.simap_backend.model.Aluno;
import com.simap.simap_backend.repository.AlunoRepository;
import com.simap.simap_backend.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    /*
    * Busca aluno por RA
    * Verifica se o RA foi informado
    * Lança exceção se não encontrar*/

    public Aluno obterPorRA(String ra){
        if (ra == null || ra.isBlank()){
            throw new IllegalArgumentException("RA é obrigatório.");
        }
        return alunoRepository.findByRa(ra)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado para o RA " + ra));
    }

    /*
     * Monta o DTO completo usado pela página "individual aluno".
     * Aqui é onde agregamos dados de outras tabelas (matrícula/turma, diagnósticos, frequência, projetos, etc.).
     * Por enquanto, deixei valores de exemplo (stubs) só para a rota funcionar — você pluga os repositórios reais depois.
     */

    public AlunoDTO obterCompletoporRA(String ra){
        //Entidade base
        Aluno aluno = obterPorRA(ra);

        // dados acadêmicos atuais (virão da matrícula/turma no banco)
        String escola = alunoRepository.findEscolaByRa(aluno.getRa()) != null ? alunoRepository.findEscolaByRa(aluno.getRa()) : "-";
        String serie  = alunoRepository.findSerieByRa(aluno.getRa()) != null ? alunoRepository.findSerieByRa(aluno.getRa()) : "-";
        String turma  = alunoRepository.findTurmaByRa(aluno.getRa()) != null ? alunoRepository.findTurmaByRa(aluno.getRa()) : "-";
        String turno  = alunoRepository.findPeriodoByRa(aluno.getRa()) !=null ? alunoRepository.findPeriodoByRa(aluno.getRa()) : "-";
//        Integer numeroChamada = 1; // TODO: buscar do vínculo aluno_turma/matrícula

        // declaração / hipótese inicial / diagnósticos (virão de outras tabelas)
        String declaracao = aluno.getDeclaracao() != null ? aluno.getDeclaracao().getDescricaoDeclaracao(): "-";
        String mapaInicial = null;


        //chamada das hipóteses(SSVS, SA, ALF, etc)
        String hipoteseB1 = alunoRepository.findResultadoBimByRa(aluno.getRa(), 1);
        String hipoteseB2 = alunoRepository.findResultadoBimByRa(aluno.getRa(), 2);
        String hipoteseB3 = alunoRepository.findResultadoBimByRa(aluno.getRa(), 3);
        String hipoteseB4 = alunoRepository.findResultadoBimByRa(aluno.getRa(), 4);

        //chama as frequências
        String frequenciaB1 = alunoRepository.findFrequenciaByRa(aluno.getRa(), "1");
        String frequenciaB2 = alunoRepository.findFrequenciaByRa(aluno.getRa(), "2");
        String frequenciaB3 = alunoRepository.findFrequenciaByRa(aluno.getRa(), "3");
        String frequenciaB4 = alunoRepository.findFrequenciaByRa(aluno.getRa(), "4");

        //mapeamento
        //pega as chamadas armezenadas em variaveis e apresenta dentro de cada lugar
        BimestreDTO b1 = AlunoMapper.bimestre( hipoteseB1, frequenciaB1, "NÃO");
        BimestreDTO b2 = AlunoMapper.bimestre( hipoteseB2, frequenciaB2, "ALF");
        BimestreDTO b3 = AlunoMapper.bimestre( hipoteseB3, frequenciaB3, "MAT1");
        BimestreDTO b4 = AlunoMapper.bimestre( hipoteseB4, frequenciaB4, "ALF/MAT1");

        // monta o DTO final
        return AlunoMapper.toDTO(
                aluno,
                escola,
                serie,
                turma,
                turno,
                declaracao,
                mapaInicial,
                b1,
                b2,
                b3,
                b4,
                null // numeroChamada ainda não vem do banco
        );
    }

}
