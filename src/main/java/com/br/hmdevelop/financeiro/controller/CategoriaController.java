package com.br.hmdevelop.financeiro.controller;

import com.br.hmdevelop.financeiro.model.dto.CategoriaDTO;
import com.br.hmdevelop.financeiro.mapper.CategoriaMapper;
import com.br.hmdevelop.financeiro.model.Categoria;
import com.br.hmdevelop.financeiro.service.CategoriaService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaController {

    private final CategoriaService service;
    private final CategoriaMapper mapper;

    public CategoriaController(CategoriaService service, CategoriaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @POST
    public Response salvar(@Valid CategoriaDTO dto) {
        Categoria entidade = mapper.toEntity(dto);
        service.salvar(entidade);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<CategoriaDTO> listar() {
        return mapper.toDTOList(service.listar());
    }

    @GET
    @Path("/arvore")
    public List<CategoriaDTO> listarEmArvore() {
        return mapper.toDTOList(service.listarEmArvore());
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid CategoriaDTO dto) {
        Categoria entidade = mapper.toEntity(dto);
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

    @GET
    @Path("/{id}")
    public CategoriaDTO buscarPorId(@PathParam("id") Long id) {
        Categoria entidade = service.buscarPorId(id);
        return mapper.toDTO(entidade);
    }
}
