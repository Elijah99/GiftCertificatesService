package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.enums.SearchParameter;
import com.epam.esm.enums.SortParameter;
import com.epam.esm.enums.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * Provides a centralized request handling
 * to GiftCertificates resource
 *
 * @author Ilya Ramanouski
 */
@RestController
@RequestMapping("/giftCertificates")
public class GiftCertificatesController {

    private GiftCertificateService service;


    /**
     * Provides GET requests to the all GiftCertificates with parameters
     * request examples:
     *      .../giftCertificates/  - returns all list of GiftCertificates
     *      .../giftCertificates?searchBy=column&value=val  - finds GiftCertificates which contains value 'val' at column 'column'
     *      .../giftCertificates?sortBy=column&sortType=desc    - returns all GiftCertificates sorted by column 'column' in descending order
     *
     * @param searchBy (optional) request parameter for column to search
     * @param value (optional) request parameter for value to search
     * @param sortBy (optional) request parameter for column to sort
     * @param sortType (optional) request parameter for sorting order type
     * @return list of GiftCertificate.
     */
    @GetMapping
    public List<GiftCertificateDto> getAllGiftCertificates(@RequestParam(required = false) SearchParameter searchBy,
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

    /**
     * Provides GET request to the one GiftCertificate by its id
     * request example:
     *      .../giftCertificates/1  -  returns GiftCertificate with id '1'
     * @param id path variable,id of requested GiftCertificate
     * @return GiftCertificate if its presents
     * @throws GiftCertificateNotFoundException if GiftCertificate with given id is not present
     */
    @GetMapping(value = "/{id}")
    public GiftCertificateDto getGiftCertificate(@PathVariable("id") BigInteger id) {
        return service.findById(id);
    }

    /**
     * Provides POST request to add new GiftCertificate
     * request example:
     *      .../giftCertificates/  - saves new giftCertificate, requires request body in json format
     * @param giftCertificate GiftCertificate object bases on json object in request body
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGiftCertificate(@RequestBody GiftCertificateDto giftCertificate) {
        service.save(giftCertificate);
    }

    /**
     * Provides PUT mapping to update existing GiftCertificate
     * request example:
     *      *      .../giftCertificates/1  - updates giftCertificate with id '1', requires request body in json format
     * @param giftCertificate GiftCertificate object bases on json object in request body
     * @param id index of GiftCertificate which need to update
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGiftCertificate(@RequestBody GiftCertificateDto giftCertificate, @PathVariable BigInteger id) {
        service.update(giftCertificate, id);
    }
    /**
     * Provides DELETE mapping to delete existing GiftCertificate
     * request example:
     *      *      .../giftCertificates/1  - deletes giftCertificate with id '1'
     * @param id index of GiftCertificate which need to update
     */
    @DeleteMapping(value = "/{id}")
    public void deleteGiftCertificate(@PathVariable("id") BigInteger id) {
        service.deleteById(id);
    }

    @Autowired
    public void setService(GiftCertificateService service) {
        this.service = service;
    }
}
