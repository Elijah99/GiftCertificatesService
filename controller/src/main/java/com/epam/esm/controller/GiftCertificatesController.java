package com.epam.esm.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/giftCertificates")
public class GiftCertificatesController {

    private GiftCertificateService service;

    @Autowired
    public GiftCertificatesController(GiftCertificateService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = GET)
    public @ResponseBody List<GiftCertificate> getGiftCertificate(@PathVariable("id") BigInteger id) {
        return service.findAll();
    }
}
