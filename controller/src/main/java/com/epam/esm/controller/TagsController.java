package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@Controller
@RequestMapping("/Tags")
public class TagsController {

    private TagService service;

    @Autowired
    public TagsController(TagService service) {
        this.service = service;
    }

    @RequestMapping(produces = "application/json", method = GET)
    public @ResponseBody
    List<Tag> getAllTags() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = GET)
    public @ResponseBody
    Tag getTag(@PathVariable("id") BigInteger id) {
        return service.findById(id).get();
    }

    @RequestMapping(produces = "application/json", method = POST)
    public void createTag(@RequestBody Tag Tag) {
        service.save(Tag);
    }


    @RequestMapping(value = "/{id}", produces = "application/json", method = DELETE)
    public @ResponseBody
    void deleteTag(@PathVariable("id") BigInteger id) {
        service.deleteById(id);
    }
}
