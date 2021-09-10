package com.epam.esm.mapper;

public interface DtoMapper<E, T> {
    T mapEntityToDto(E entity);

    E mapDtoToEntity(T dto);
}
