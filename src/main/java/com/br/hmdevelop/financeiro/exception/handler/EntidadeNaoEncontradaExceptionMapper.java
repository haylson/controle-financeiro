package com.br.hmdevelop.financeiro.exception.handler;

import com.br.hmdevelop.financeiro.exception.EntidadeNaoEncontradaException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class EntidadeNaoEncontradaExceptionMapper implements ExceptionMapper<EntidadeNaoEncontradaException> {

    @Override
    public Response toResponse(EntidadeNaoEncontradaException exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", "Entidade não encontrada");
        erro.put("mensagem", exception.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(erro)
                .build();
    }
}
