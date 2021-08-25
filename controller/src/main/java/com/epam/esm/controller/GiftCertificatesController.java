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

    @RequestMapping(produces = "application/json", method = GET)
    public @ResponseBody
    List<GiftCertificate> getAllGiftCertificates() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = GET)
    public @ResponseBody
    GiftCertificate getGiftCertificate(@PathVariable("id") BigInteger id) {
        return service.findById(id).get();
    }

    @RequestMapping(produces = "application/json", method = POST)
    public void createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        service.save(giftCertificate);
    }

    @RequestMapping(produces = "application/json", method = PATCH)
    public void updateGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        service.update(giftCertificate);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = DELETE)
    public @ResponseBody
    void deleteGiftCertificate(@PathVariable("id") BigInteger id) {
        service.deleteById(id);
    }

}
