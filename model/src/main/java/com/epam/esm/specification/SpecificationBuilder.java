package com.epam.esm.specification;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.specification.impl.SearchByStringGiftCertificateSpecification;
import com.epam.esm.specification.impl.SearchByTagGiftCertificateSpecification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpecificationBuilder {
    public List<PredicateSpecification<GiftCertificate>> createPredicateSpecifications(QueryParameters parameters) {
        List<PredicateSpecification<GiftCertificate>> specifications = new ArrayList<>();

        if (parameters.getSearchValue() != null && parameters.getSearchParameter() != null) {
            if (!parameters.getSearchParameter().equals("tags")) {
                parameters.getSearchValue().forEach(searchValue ->
                        specifications.add(new SearchByStringGiftCertificateSpecification(parameters.getSearchParameter(), searchValue))
                );
            }
            if (parameters.getSearchParameter().equals("tags")) {
                parameters.getSearchValue().forEach(searchValue ->
                        specifications.add(new SearchByTagGiftCertificateSpecification(searchValue))
                );
            }
        } else if(parameters.getSearchValue() != null){
            parameters.getSearchValue().forEach(searchValue ->
                    specifications.add(new SearchByStringGiftCertificateSpecification(parameters.getSearchParameter(), searchValue))
            );
        }

        return specifications;
    }
}