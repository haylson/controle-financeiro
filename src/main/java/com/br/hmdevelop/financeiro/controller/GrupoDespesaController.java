package com.br.hmdevelop.financeiro.controller;

import com.br.hmdevelop.financeiro.mapper.GrupoDespesaMapper;
import com.br.hmdevelop.financeiro.model.GrupoDespesa;
import com.br.hmdevelop.financeiro.model.dto.GrupoDespesaDTO;
import com.br.hmdevelop.financeiro.service.GrupoDespesaService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/grupo-despesa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GrupoDespesaController {

    private final GrupoDespesaService service;
    private final GrupoDespesaMapper mapper;

    public GrupoDespesaController(GrupoDespesaService service, GrupoDespesaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    public List<GrupoDespesaDTO> listar() {
        return mapper.toDTOList(service.listar());
    }

    @POST
    public Response salvar(@Valid GrupoDespesaDTO dto) {
        service.salvar(mapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid GrupoDespesaDTO dto) {
        GrupoDespesa entidade = mapper.toEntity(dto);
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