package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private TagDaoImpl dao;

    public TagServiceImpl() {
    }

    @Override
    public List<Tag> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Tag> findById(BigInteger id) {
        return dao.findById(id);
    }

    @Override
    public void update(Tag tag) {

    }

    @Override
    public void deleteById(BigInteger id) {
        dao.deleteById(id);
    }

    @Override
    public void save(Tag tag) {
        dao.save(tag);
    }

    @Autowired
    public void setDao(TagDaoImpl dao) {
        this.dao = dao;
    }
}
