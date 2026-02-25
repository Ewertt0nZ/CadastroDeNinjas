package de.java10x.CadastroDeNinjas.Missoes;

import de.java10x.CadastroDeNinjas.Ninjas.Controller.Service.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesDTO {


    private Long id;
    private String nomeMissao;
    private String dificuldade;
    private String imgUrl;

    private List<NinjaModel> ninjas;


}
