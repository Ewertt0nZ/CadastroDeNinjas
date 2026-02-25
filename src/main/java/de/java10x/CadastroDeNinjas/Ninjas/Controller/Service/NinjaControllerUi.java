package de.java10x.CadastroDeNinjas.Ninjas.Controller.Service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "ninjaListados"; // TEM QUE RETORNAR O NOME DA PAGINA QUE RENDERIZA
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model){
        NinjaDTO ninjaBuscado = ninjaService.listarNinjasPorId(id);
        if (ninjaBuscado != null) {
            model.addAttribute("ninjas", ninjaBuscado);
            return "detalhesNinja";
        }else {
            model.addAttribute("mensagem","Ninja não encontrado");
            return "ninjaListados";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model){
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes){
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String alterarNinja(@PathVariable Long id, Model model){
        return "alterarNinja";
    }

}
