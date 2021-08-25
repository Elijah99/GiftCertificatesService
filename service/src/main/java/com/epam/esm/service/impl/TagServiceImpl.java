package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Optional<Tag> findById(BigInteger id) {
        return Optional.empty();
    }

    @Override
    public void update(Tag tag) {

    }

    @Override
    public void deleteById(BigInteger id) {

    }

    @Override
    public void save(Tag tag) {

    }
}
