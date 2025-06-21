package com.br.hmdevelop.financeiro.model;

import lombok.Data;

import java.util.List;

@Data
public class Categoria {
    private Long id;
    private String descricao;
    private Long categoriaPaiId; // Autorreferência

    // Não vem do banco diretamente, mas será montado na aplicação
    private List<Categoria> filhos;
}
