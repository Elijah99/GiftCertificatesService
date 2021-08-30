package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

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

    /**
     * Provides GET request to the list of all Tags
     * request example:
     *      .../tags/  - returns all list of Tags
     *
     * @return list of Tags.
     */
    @GetMapping
    public List<Tag> getAllTags() {
        return service.findAll();
    }

    /**
     * Provides GET request to the one Tags by its id
     * request example:
     *      .../tags/1  -  returns GiftCertificate with id '1'
     * @param id path variable,id of requested GiftCertificate
     * @return Tag if its presents
     * @throws TagNotFoundException if Tag with given id is not present
     */
    @GetMapping(value = "/{id}")
    public Tag getTag(@PathVariable("id") BigInteger id) {
        return service.findById(id);
    }

    /**
     * Provides POST request to add new Tag
     * request example:
     *      .../tags/  - saves new Tag, requires request body in json format
     * @param tag Tag object bases on json object in request body
     */
    @PostMapping
    public void createTag(@RequestBody Tag tag) {
        service.save(tag);
    }

    /**
     * Provides DELETE mapping to delete existing Tag
     * request example:
     *      *      .../tags/1  - deletes Tag with id '1'
     * @param id index of Tag which need to update
     */
    @DeleteMapping(value = "/{id}")
    public void deleteTag(@PathVariable("id") BigInteger id) {
        service.deleteById(id);
    }

    @Autowired
    public void setService(TagService service) {
        this.service = service;
    }
}
