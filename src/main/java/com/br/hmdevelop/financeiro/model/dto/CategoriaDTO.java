package com.br.hmdevelop.financeiro.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
    private String descricao;

    private Long categoriaPaiId;

    // recursivo opcional - não precisa ser validado para entrada
    private List<CategoriaDTO> filhos;
}
