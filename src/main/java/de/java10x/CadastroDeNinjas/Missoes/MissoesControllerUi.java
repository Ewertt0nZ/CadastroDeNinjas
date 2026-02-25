package de.java10x.CadastroDeNinjas.Missoes;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "missoesListadas";
    }

    @GetMapping("/listar/{id}")
    public String listarMissoesPorId(@PathVariable Long id, Model model) {
        MissoesDTO missaoBuscada = missoesService.listarMissaoPorId(id);
        if (missaoBuscada != null) {
            model.addAttribute("missoes", missaoBuscada);
            return "detalhesMissoes";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada");
            return "missoesListadas";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioMissoes(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão cadastrada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissaoPorId(@PathVariable long id){
        missoesService.deletarMissao(id);
        return "redirect/missoes/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String alterarMissao(@PathVariable Long id, Model model){
        return "alterarMissao";
    }

}












