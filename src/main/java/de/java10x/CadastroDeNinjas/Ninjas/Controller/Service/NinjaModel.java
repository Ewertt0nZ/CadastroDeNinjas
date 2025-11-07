package de.java10x.CadastroDeNinjas.Ninjas.Controller.Service;


import de.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_Url")
    private String imgUrl;

    @Column(name = "Idade")
    private int idade;

    // @ManyToOne muitos ninjas tem uma unica missão
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreign key ou Chave Estrangeira
    private MissoesModel missoes;





}
