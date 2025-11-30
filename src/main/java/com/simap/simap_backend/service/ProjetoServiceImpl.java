package com.simap.simap_backend.service;

import com.simap.simap_backend.dto.FiltroProjetoRequest;
import com.simap.simap_backend.dto.ProjetoRelatorioDTO;
import com.simap.simap_backend.mapper.ProjetoRelatorioMapper;
import com.simap.simap_backend.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository){
        this.projetoRepository = projetoRepository;
    }

    @Override
    public List<ProjetoRelatorioDTO> filtrarProjetos(FiltroProjetoRequest filtro){

        if (filtro.anoLetivo() == null || filtro.anoLetivo().isEmpty()) {
            throw new IllegalArgumentException("Selecione pelo menos um Ano Letivo");
        }

        if (filtro.escolas() == null || filtro.escolas().isEmpty()) {
            throw new IllegalArgumentException("Selecione pelo menos uma Escola");
        }

        //1) Buscar dados crus no banco
        List<Map<String, Object>> linhas = projetoRepository.filtrarProjetos(filtro);

        //2) Converter usando o mapper
        return ProjetoRelatorioMapper.map(linhas);
    }
}
