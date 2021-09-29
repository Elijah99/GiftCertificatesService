package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.hateoas.GiftCertificatesLinkManager;
import com.epam.esm.hateoas.representation.GiftCertificateRepresentation;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides a centralized request handling
 * to GiftCertificates resource
 *
 * @author Ilya Ramanouski
 */
@RestController
@RequestMapping(value = "/giftCertificates")
public class GiftCertificatesController {

    private GiftCertificateService service;
    private GiftCertificatesLinkManager giftCertificatesLinkManager;

    /**
     * Provides GET requests to the all GiftCertificates with parameters
     * request examples:
     * .../giftCertificates/  - returns all list of GiftCertificates
     * .../giftCertificates?searchBy=column&value=val  - finds GiftCertificates which contains value 'val' at column 'column'
     * .../giftCertificates?sortBy=column&sortType=desc    - returns all GiftCertificates sorted by column 'column' in descending order
     *
     * @param sortType        (optional) request parameter for sorting order type
     * @param sortValue       (optional) request parameter for column to sort
     * @param searchParameter (optional) request parameter for column to search
     * @param searchValue     (optional) request parameter for value to search
     * @return list of GiftCertificate.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<GiftCertificateRepresentation> getAllGiftCertificates(@RequestParam(required = false, defaultValue = "1") int page,
                                                                                 @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                                                 @RequestParam(required = false) String sortType,
                                                                                 @RequestParam(required = false) String sortValue,
                                                                                 @RequestParam(required = false) String searchParameter,
                                                                                 @RequestParam(required = false) String searchValue) {
        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        List<GiftCertificateRepresentation> links = service.findAll(requestParameters).stream().map(GiftCertificateRepresentation::new).collect(Collectors.toList());

        return giftCertificatesLinkManager.createLinks(links, requestParameters);
    }

    /**
     * Provides GET request to the one GiftCertificate by its id
     * request example:
     * .../giftCertificates/1  - returns GiftCertificate with id '1'
     *
     * @param id path variable,id of requested GiftCertificate
     * @return GiftCertificate if its presents
     * @throws GiftCertificateNotFoundException if GiftCertificate with given id is not present
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateRepresentation getGiftCertificate(@PathVariable("id") BigInteger id) {
        GiftCertificateDto giftCertificateDto = service.findById(id);
        return new GiftCertificateRepresentation(giftCertificateDto);
    }

    /**
     * Provides POST request to add new GiftCertificate
     * request example:
     * .../giftCertificates/  - saves new giftCertificate, requires request body in json format
     *
     * @param giftCertificate GiftCertificate object bases on json object in request body
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGiftCertificate(@RequestBody GiftCertificateDto giftCertificate) {
        service.save(giftCertificate);
    }

    /**
     * Provides PUT mapping to update existing GiftCertificate
     * request example:
     * *      .../giftCertificates/1  - updates giftCertificate with id '1', requires request body in json format
     *
     * @param giftCertificate GiftCertificate object bases on json object in request body
     * @param id              index of GiftCertificate which need to update
     * @return
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateRepresentation updateGiftCertificate(@RequestBody GiftCertificateDto giftCertificate, @PathVariable BigInteger id) {
        GiftCertificateDto giftCertificateDto = service.update(giftCertificate, id);
        return new GiftCertificateRepresentation(giftCertificateDto);
    }

    /**
     * Provides DELETE mapping to delete existing GiftCertificate
     * request example:
     * *      .../giftCertificates/1  - deletes giftCertificate with id '1'
     *
     * @param id index of GiftCertificate which need to update
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigInteger deleteGiftCertificate(@PathVariable("id") BigInteger id) {
        return service.deleteById(id);
    }

    @Autowired
    public void setService(GiftCertificateService service) {
        this.service = service;
    }

    @Autowired
    public void setGiftCertificatesLinkManager(GiftCertificatesLinkManager giftCertificatesLinkManager) {
        this.giftCertificatesLinkManager = giftCertificatesLinkManager;
    }
}
