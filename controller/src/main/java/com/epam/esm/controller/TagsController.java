package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private TagService service;

    @Autowired
    public TagsController() {
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Tag getTag(@PathVariable("id") BigInteger id) {
        return service.findById(id);
    }

    @PostMapping
    public void createTag(@RequestBody Tag Tag) {
        service.save(Tag);
    }


    @DeleteMapping(value = "/{id}")
    public void deleteTag(@PathVariable("id") BigInteger id) {
        service.deleteById(id);
    }

    @Autowired
    public void setService(TagService service) {
        this.service = service;
    }
}
