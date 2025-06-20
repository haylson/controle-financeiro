package com.br.hmdevelop.financeiro.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoCredito {
    private Long id;
    private String descricao;
    private String bandeira;
    private String ultimosQuatroDigitos;
    private int vencimento; // Dia do mês
    private BigDecimal limiteTotal;
    private BigDecimal limiteLivre;


}
