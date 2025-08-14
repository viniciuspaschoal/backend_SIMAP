package com.simap.simap_backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "escola")
public class Escola {

    @Id
    @Column(name = "codEscola", length = 7)
    private String codEscola;

    @Column(name = "nome_escola", length = 200)
    private String nomeEscola;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "end_rua", length = 100)
    private String endRua;

    @Column(name = "end_bairro", length = 100)
    private String endBairro;

    @Column(name = "end_numero", length = 10)
    private String endNumero;

    @Column(name = "end_cidade", length = 100)
    private String endCidade;

    @Column(name = "end_estado", length = 2)
    private String endEstado;

    @Column(name = "end_cep", length = 10)
    private String endCep;

    @Column(name = "end_complemento")
    private String endCompleto;

    //Relacionamento com Turma
    @OneToMany(mappedBy = "escola")
    private List<Turma> turmas;

}
