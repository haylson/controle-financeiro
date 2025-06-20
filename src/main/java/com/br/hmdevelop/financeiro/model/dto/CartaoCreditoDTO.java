package com.br.hmdevelop.financeiro.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoCreditoDTO {

    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "A bandeira é obrigatória")
    private String bandeira;

    @Pattern(regexp = "\\d{4}", message = "Deve conter exatamente 4 dígitos")
    private String ultimosQuatroDigitos;

    @Min(value = 1, message = "O vencimento deve ser entre 1 e 31")
    @Max(value = 31, message = "O vencimento deve ser entre 1 e 31")
    private int vencimento;

    @DecimalMin(value = "0.01", message = "Limite total deve ser positivo")
    private BigDecimal limiteTotal;

    @DecimalMin(value = "0.00", message = "Limite livre não pode ser negativo")
    private BigDecimal limiteLivre;

    // Getters e Setters
    // Você pode usar Lombok também se preferir
    // @Data se tiver lombok configurado
    // ...
}
