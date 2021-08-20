package com.epam.esm.service;


import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftCertificateService {



    public List<GiftCertificate> findAll(){
        return new ArrayList<GiftCertificate>();
    }
}
