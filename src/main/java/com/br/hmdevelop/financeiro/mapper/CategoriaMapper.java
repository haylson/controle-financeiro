package com.br.hmdevelop.financeiro.mapper;

import com.br.hmdevelop.financeiro.model.Categoria;
import com.br.hmdevelop.financeiro.model.dto.CategoriaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CategoriaMapper {
    Categoria toEntity(CategoriaDTO dto);
    CategoriaDTO toDTO(Categoria entity);
    List<CategoriaDTO> toDTOList(List<Categoria> list);
}
