package com.epam.esm.controller;

import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.hateoas.TagLinkManager;
import com.epam.esm.hateoas.representation.TagRepresentation;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides a centralized request handling
 * to Tags resource
 *
 * @author Ilya Ramanouski
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    private TagService service;

    private TagLinkManager tagLinkManager;

    /**
     * Provides GET request to the list of all Tags
     * request example:
     * .../tags/  - returns all list of Tags
     *
     * @param page            requested page
     * @param pageSize        requested number of rows at page
     * @param sortType        asc/desc value for order rows
     * @param sortValue       column to sort rows
     * @param searchParameter column to search
     * @param searchValue     list of values to search
     * @return CollectionModel of TagRepresentation.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<TagRepresentation> getAllTags(@RequestParam(required = false, defaultValue = "1") int page,
                                                         @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                         @RequestParam(required = false) String sortType,
                                                         @RequestParam(required = false) String sortValue,
                                                         @RequestParam(required = false) String searchParameter,
                                                         @RequestParam(required = false) List<String> searchValue) {
        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        List<TagRepresentation> links = service.findAll(requestParameters).stream().map(TagRepresentation::new).collect(Collectors.toList());

        return tagLinkManager.createLinks(links, requestParameters);
    }

    /**
     * Provides GET request to the one Tags by its id
     * request example:
     * .../tags/1  -  returns User with id '1'
     *
     * @param id path variable,id of requested GiftCertificate
     * @return TagRepresentation if its presents
     * @throws TagNotFoundException if Tag with given id is not present
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagRepresentation getTag(@PathVariable("id") BigInteger id) {
        TagDto tagDto = service.findById(id);
        return new TagRepresentation(tagDto);
    }

    /**
     * Provides POST request to add new Tag
     * request example:
     * .../tags/  - saves new Tag, requires request body in json format
     *
     * @param tag TagDto object bases on json object in request body
     * @return created TagRepresentation
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagRepresentation createTag(@RequestBody TagDto tag) {
        TagDto tagDto = service.save(tag);
        return new TagRepresentation(tagDto);
    }

    /**
     * Provides DELETE mapping to delete existing Tag
     * request example:
     * *      .../tags/1  - deletes Tag with id '1'
     *
     * @param id index of Tag which need to update
     * @return id of deleted tag
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigInteger deleteTag(@PathVariable("id") BigInteger id) {
        return service.deleteById(id);
    }

    /**
     * Provides GET mapping to find most widely used
     * tag of a user with highest cost of all orders
     *
     * @return TagRepresentation if its present
     */
    @GetMapping(value = "mostUsedTag")
    @ResponseStatus(HttpStatus.OK)
    public TagRepresentation getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders() {
        TagDto tagDto = service.getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders();
        return new TagRepresentation(tagDto);
    }

    @Autowired
    public void setService(TagService service) {
        this.service = service;
    }

    @Autowired
    public void setTagLinkManager(TagLinkManager tagLinkManager) {
        this.tagLinkManager = tagLinkManager;
    }
}
