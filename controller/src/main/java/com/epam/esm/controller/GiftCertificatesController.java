package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.hateoas.GiftCertificatesLinkManager;
import com.epam.esm.hateoas.representation.GiftCertificateRepresentation;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
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
     * .../giftCertificates/page=1&pageSize=10  - returns first 10 records of GiftCertificates
     *
     * @param page            requested page
     * @param pageSize        requested number of rows at page
     * @param sortType        asc/desc value for order rows
     * @param sortValue       column to sort rows
     * @param searchParameter column to search
     * @param searchValue     list of values to search
     * @return CollectionModel of GiftCertificateRepresentation.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<GiftCertificateRepresentation> getAllGiftCertificates(@RequestParam(required = false, defaultValue = "1") int page,
                                                                                 @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                                                 @RequestParam(required = false) String sortType,
                                                                                 @RequestParam(required = false) String sortValue,
                                                                                 @RequestParam(required = false) String searchParameter,
                                                                                 @RequestParam(required = false) List<String> searchValue) {
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
     * @return GiftCertificateRepresentation if its presents
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
     * @return created GiftCertificateRepresentation
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateRepresentation createGiftCertificate(@RequestBody GiftCertificateDto giftCertificate) {
        GiftCertificateDto dto = service.save(giftCertificate);
        return new GiftCertificateRepresentation(dto);
    }

    /**
     * Provides PUT mapping to update existing GiftCertificate
     * request example:
     * *      .../giftCertificates/1  - updates giftCertificate with id '1', requires request body in json format
     *
     * @param giftCertificate GiftCertificate object bases on json object in request body
     * @param id              index of GiftCertificate which need to update
     * @return updated GiftCertificateRepresentation
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
     * @return id of deleted GiftCertificate
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
