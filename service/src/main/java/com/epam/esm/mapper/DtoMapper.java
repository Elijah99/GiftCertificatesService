package com.epam.esm.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoMapper<E, T> {
    T mapEntityToDto(E entity);

    E mapDtoToEntity(T dto);

    default public List<T> mapListEntityToListDto(List<E> entities) {
        return entities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    default public List<E> mapListDtoToListEntity(List<T> dtos) {
        return dtos.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());
    }
}
