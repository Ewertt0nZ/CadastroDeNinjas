package de.java10x.CadastroDeNinjas.Missoes;

import de.java10x.CadastroDeNinjas.Ninjas.Controller.Service.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "tb_missoes")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMissao;
    private String dificuldade;

    // @OneToMany uma missão tem muitos ninjas
    @OneToMany (mappedBy = "missoes")
    private List<NinjaModel> ninjas;





}
