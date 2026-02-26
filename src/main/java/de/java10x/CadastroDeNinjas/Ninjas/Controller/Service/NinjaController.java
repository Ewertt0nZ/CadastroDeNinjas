package de.java10x.CadastroDeNinjas.Ninjas.Controller.Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e inseri no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<String> criarNinja(
            @Parameter(description = "Usuário manda os dados do ninja a ser criado no corpo da requisição")
            @RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostrar todos os Ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista os ninjas", description = "Rota Lista todos os ninjas cadastrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ninjas encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista não foi econtrada")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar Ninja por ID (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por ID", description = "Rota lista um ninja pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id){
        NinjaDTO ninjaBuscado = ninjaService.listarNinjasPorId(id);
        if (ninjaBuscado != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(ninjaBuscado);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja do ID " + id + " não existe nos nossos registros");
        }
    }

    // Alterar dados dos Ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por ID", description = "Rota altera um ninja pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possivel alterar")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null) {
           return ResponseEntity.status(HttpStatus.OK)
                   .body(ninja);
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Ninja com o ID " + id + " não foi encontrado");
       }
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por ID", description = "Rota deleta um ninja existente pelo seu ID no banco de dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possivel excluir")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id){
        if (ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " não foi encontrado");
        }
    }


}
