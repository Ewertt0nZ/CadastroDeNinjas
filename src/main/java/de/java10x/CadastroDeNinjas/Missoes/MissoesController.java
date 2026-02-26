package de.java10x.CadastroDeNinjas.Missoes;


// LOCALHOST:8080

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

//  LOCALHOST:8080/MISSOES/CRIAR

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    @Operation(summary = "Lista as missões", description = "Rota lista todas as missões cadastradas no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de missões encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "lista de missões não encontrada")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissao(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista missão por ID", description = "Rota lista uma missão pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<?> listarMissaoPorId(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id){
        MissoesDTO missoesID = missoesService.listarMissaoPorId(id);
        if (missoesID != null){
            return ResponseEntity.ok(missoesID);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe em nossos registros");
        }
    }

    // POST -- Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missão", description = "Rota cria uma nova missão e inseri no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão")
    })
    public ResponseEntity<String> criarMissao(
            @Parameter(description = "Usuário manda os dados da missão a ser criada no corpo da requisição")
            @RequestBody MissoesDTO missao){
        MissoesDTO novaMissao = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + novaMissao.getNomeMissao() + " ID: " + novaMissao.getId());
    }

    // PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera a missão por ID", description = "Rota altera uma missão pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada, não foi possível alterar")
    })
    public ResponseEntity<?> alterarMissaoPorId(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados da missão a ser atualizada no corpo da requisição")
            @RequestBody MissoesDTO missaoAtualizada){
        MissoesDTO missao = missoesService.atualizarMissao(id, missaoAtualizada);
        if (missao != null){
            return ResponseEntity.ok(missao);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe em nossos registros");
        }
    }

    // DELETE -- Mandar uma requisição para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta missão por ID", description = "Rota deleta uma missão existente pelo seu ID no banco de dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada, não foi possível excluir")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id){
        if (missoesService.listarMissaoPorId(id)!= null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missão com o ID " + id + "foi deletada com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe em nossos registros");
        }
    }


}
