package com.br.hmdevelop.financeiro.mapper;

import com.br.hmdevelop.financeiro.model.GrupoDespesa;
import com.br.hmdevelop.financeiro.model.dto.GrupoDespesaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface GrupoDespesaMapper {
    GrupoDespesaDTO toDTO(GrupoDespesa entidade);
    GrupoDespesa toEntity(GrupoDespesaDTO dto);
    List<GrupoDespesaDTO> toDTOList(List<GrupoDespesa> entidades);
}
