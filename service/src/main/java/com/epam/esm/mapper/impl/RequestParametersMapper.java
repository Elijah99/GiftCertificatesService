package com.epam.esm.mapper.impl;

import com.epam.esm.entity.QueryParameters;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.mapper.DtoMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestParametersMapper implements DtoMapper<QueryParameters, RequestParameters> {
    @Override
    public RequestParameters mapEntityToDto(QueryParameters entity) {
        return new RequestParameters(entity.getCurrentPage(),
                entity.getPageSize(),
                entity.getSortType(),
                entity.getSortValue(),
                entity.getSearchParameter(),
                entity.getSearchValue());
    }

    @Override
    public QueryParameters mapDtoToEntity(RequestParameters dto) {
        return new QueryParameters(dto.getCurrentPage(),
                dto.getPageSize(),
                dto.getSortType(),
                dto.getSortValue(),
                dto.getSearchParameter(),
                dto.getSearchValue());
    }
}
