package com.epam.esm.hateoas;

import com.epam.esm.dto.RequestParameters;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface HateoasLinkManager<T> {
    CollectionModel<T> createLinks(List<T> list, RequestParameters requestParameters);
}
