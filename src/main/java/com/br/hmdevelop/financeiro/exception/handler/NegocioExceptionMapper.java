package com.br.hmdevelop.financeiro.exception.handler;

import com.br.hmdevelop.financeiro.exception.NegocioException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class NegocioExceptionMapper implements ExceptionMapper<NegocioException> {

    @Override
    public Response toResponse(NegocioException exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(erro)
                .build();
    }
}