package de.java10x.CadastroDeNinjas.Ninjas.Controller.Service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // LISTAR TODOS OS MEUS NINJAS
    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    }

    // LISTAR TODOS OS MEUS NINJAS POR ID
    public NinjaModel listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

}
