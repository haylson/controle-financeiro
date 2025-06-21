package com.br.hmdevelop.financeiro.service;

import com.br.hmdevelop.financeiro.model.Categoria;
import com.br.hmdevelop.financeiro.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Categoria categoria) {
        repository.inserir(categoria);
    }

    public List<Categoria> listar() {
        return repository.listarTodos();
    }

    public List<Categoria> listarEmArvore() {
        return repository.listarHierarquia();
    }

    public void atualizar(Categoria categoria) {
        repository.atualizar(categoria);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }

    public Categoria buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }
}
