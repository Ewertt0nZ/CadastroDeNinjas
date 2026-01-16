package de.java10x.CadastroDeNinjas.Missoes;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // LISTAR TODAS AS MISSÕES
    public List <MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();
       return missoes.stream()
                .map(missoesMapper::map)
               .collect(Collectors.toList());
    }

    // LISTAR MISSÕES POR ID
    public MissoesDTO listarMissaoPorId(Long id) {
        Optional <MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.map(missoesMapper::map).orElse(null);
    }


    //CRIAR UMA NOVA MISSÃO
    public MissoesDTO criarMissao(MissoesDTO missoesDTO){
        MissoesModel missao = missoesMapper.map(missoesDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }

    // DELETAR MISSÃO
    public void deletarMissao(Long id){
        missoesRepository.deleteById(id);
    }


    // ATUALIZAR MISSÃO
    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()){
            MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
}
