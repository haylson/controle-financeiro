package com.br.hmdevelop.financeiro.exception.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Map<String, String> erros = new HashMap<>();
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        for (ConstraintViolation<?> v : violations) {
            String campo = v.getPropertyPath().toString();
            if (campo.contains(".")) {
                campo = campo.substring(campo.lastIndexOf('.') + 1);
            }
            erros.put(campo, v.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(erros)
                .build();
    }
}
