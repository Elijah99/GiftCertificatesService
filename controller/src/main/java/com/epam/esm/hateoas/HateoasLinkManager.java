package com.epam.esm.hateoas;

import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface HateoasLinkManager<T> {
    CollectionModel<T> createLinks(List<T> list, RequestParameters requestParameters);
}
