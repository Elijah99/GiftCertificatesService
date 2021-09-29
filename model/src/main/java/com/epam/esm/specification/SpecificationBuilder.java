package com.epam.esm.specification;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.specification.impl.SearchByStringGiftCertificateSpecification;
import com.epam.esm.specification.impl.SearchByTagGiftCertificateSpecification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationBuilder {
    public List<PredicateSpecification<GiftCertificate>> createPredicateSpecifications(QueryParameters parameters) {
        List<PredicateSpecification<GiftCertificate>> specifications = new ArrayList<>();

        if(parameters.getSearchValue() != null && parameters.getSearchParameter()!=null){
            if(!parameters.getSearchParameter().equals("tags")) {
                specifications.add(new SearchByStringGiftCertificateSpecification(parameters.getSearchParameter(), parameters.getSearchValue()));
            }
            if(parameters.getSearchParameter().equals("tags")){
                specifications.add(new SearchByTagGiftCertificateSpecification(parameters.getSearchValue()));
            }
        }

        return specifications;
    }
}