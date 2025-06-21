package com.br.hmdevelop.financeiro.service;

import com.br.hmdevelop.financeiro.model.GrupoDespesa;
import com.br.hmdevelop.financeiro.repository.GrupoDespesaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GrupoDespesaService {

    private final GrupoDespesaRepository repository;

    public GrupoDespesaService(GrupoDespesaRepository repository) {
        this.repository = repository;
    }

    public void salvar(GrupoDespesa grupo) {
        repository.inserir(grupo);
    }

    public List<GrupoDespesa> listar() {
        return repository.listarTodos();
    }

    public void atualizar(GrupoDespesa grupo) {
        repository.atualizar(grupo);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
