package com.br.hmdevelop.financeiro.mapper;

import com.br.hmdevelop.financeiro.model.CartaoCredito;
import com.br.hmdevelop.financeiro.model.dto.CartaoCreditoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CartaoCreditoMapper {

    CartaoCreditoDTO toDTO(CartaoCredito entity);

    CartaoCredito toEntity(CartaoCreditoDTO dto);

    List<CartaoCreditoDTO> toDTOList(List<CartaoCredito> list);

    List<CartaoCredito> toEntityList(List<CartaoCreditoDTO> list);
}
