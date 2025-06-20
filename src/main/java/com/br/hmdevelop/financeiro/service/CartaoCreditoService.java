package com.br.hmdevelop.financeiro.service;

import com.br.hmdevelop.financeiro.exception.NegocioException;
import com.br.hmdevelop.financeiro.model.CartaoCredito;
import com.br.hmdevelop.financeiro.repository.CartaoCreditoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CartaoCreditoService {

    private final CartaoCreditoRepository repository;

    public CartaoCreditoService(CartaoCreditoRepository repository) {
        this.repository = repository;
    }

    public void salvar(CartaoCredito cartao) {
        if (cartao.getLimiteLivre().compareTo(cartao.getLimiteTotal()) > 0) {
            throw new NegocioException("Limite livre não pode ser maior que o limite total.");
        }

        repository.inserir(cartao);
    }

    public List<CartaoCredito> listar() {
        return repository.listarTodos();
    }

    public void atualizar(CartaoCredito cartao) {
        // Aqui você pode adicionar validações ou regras de negócio antes da atualização
        repository.atualizar(cartao);
    }

    public void deletar(Long id) {
        // Pode validar se o cartão existe antes de deletar, se necessário
        repository.deletar(id);
    }
}