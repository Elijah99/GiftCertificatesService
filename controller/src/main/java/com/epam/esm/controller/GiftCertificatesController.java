package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.utils.SearchParameter;
import com.epam.esm.service.utils.SortParameter;
import com.epam.esm.service.utils.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/giftCertificates")
public class GiftCertificatesController {

    private GiftCertificateService service;

    @Autowired
    public GiftCertificatesController() {
    }

    @GetMapping
    public List<GiftCertificate> getAllGiftCertificates(@RequestParam(required = false) SearchParameter searchBy,
                                                        @RequestParam(required = false) String value,
                                                        @RequestParam(required = false) SortParameter sortBy,
                                                        @RequestParam(required = false) SortType sortType) {
        if (searchBy != null && value != null) {
            return service.searchByValue(searchBy, value);
        }
        if (sortBy != null && sortType != null) {
            return service.sortByParameter(sortBy, sortType);
        }
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public GiftCertificate getGiftCertificate(@PathVariable("id") BigInteger id) {
        return service.findById(id).get();
    }

    @PostMapping
    public void createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        service.save(giftCertificate);
    }

    @PutMapping(value = "/{id}")
    public void updateGiftCertificate(@RequestBody GiftCertificate giftCertificate, @PathVariable BigInteger id) {
        service.update(giftCertificate, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGiftCertificate(@PathVariable("id") BigInteger id) {
        service.deleteById(id);
    }

    @Autowired
    public void setService(GiftCertificateService service) {
        this.service = service;
    }
}
