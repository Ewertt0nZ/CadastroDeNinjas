package de.java10x.CadastroDeNinjas.Missoes;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // LISTAR TODAS AS MISSÕES
    public List <MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    // LISTAR MISSÕES POR ID
    public MissoesModel listarMissaoPorId(Long id) {
        Optional <MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.orElse(null);
    }


    //CRIAR UMA NOVA MISSÃO
    public MissoesModel criarMissao(MissoesModel missao){
        return missoesRepository.save(missao);
    }

    // DELETAR MISSÃO
    public void deletarMissao(Long id){
        missoesRepository.deleteById(id);
    }


    // ATUALIZAR MISSÃO
    public MissoesModel atualizarMissao(Long id, MissoesModel missaoAtualizada){
        if (missoesRepository.existsById(id)){
            missaoAtualizada.setId(id);
            return missoesRepository.save(missaoAtualizada);
        }
        return null;
    }
}
