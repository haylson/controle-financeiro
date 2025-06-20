package com.br.hmdevelop.financeiro.controller;

import com.br.hmdevelop.financeiro.mapper.CartaoCreditoMapper;
import com.br.hmdevelop.financeiro.model.CartaoCredito;
import com.br.hmdevelop.financeiro.model.dto.CartaoCreditoDTO;
import com.br.hmdevelop.financeiro.service.CartaoCreditoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cartoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoCreditoController {

    private final CartaoCreditoService service;

    private final CartaoCreditoMapper mapper;

    public CartaoCreditoController(CartaoCreditoService service, CartaoCreditoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    public List<CartaoCreditoDTO> listar() {
        return mapper.toDTOList(service.listar());
    }

    @POST
    public Response salvar(@Valid CartaoCreditoDTO dto) {
        service.salvar(mapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid CartaoCreditoDTO dto) {
        CartaoCredito entidade = mapper.toEntity(dto);
        entidade.setId(id);
        service.atualizar(entidade);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }

}