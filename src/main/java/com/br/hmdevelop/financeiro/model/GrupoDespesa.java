package com.br.hmdevelop.financeiro.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GrupoDespesa {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
}
