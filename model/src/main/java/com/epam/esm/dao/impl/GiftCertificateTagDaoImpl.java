package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.entity.GiftCertificateTag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Optional;

@Repository
public class GiftCertificateTagDaoImpl implements GiftCertificateTagDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public GiftCertificateTagDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<GiftCertificateTag> findById(Long id) {
        GiftCertificateTag foundGiftCertificateTag = entityManager.find(GiftCertificateTag.class, id);
        return Optional.ofNullable(foundGiftCertificateTag);
    }

    @Override
    public GiftCertificateTag update(GiftCertificateTag giftCertificateTag) {
        try {
            entityManager.merge(giftCertificateTag);
        } finally {
            entityManager.close();
        }
        return findById(giftCertificateTag.getId()).get();
    }

    @Override
    public Long deleteById(Long id) {
        try {
            GiftCertificateTag giftCertificateTag = entityManager.find(GiftCertificateTag.class, id);
            if (giftCertificateTag != null) {
                entityManager.remove(giftCertificateTag);
                return id;
            }
        } finally {
            entityManager.close();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public GiftCertificateTag save(GiftCertificateTag giftCertificateTag) {
        try {
            entityManager.persist(giftCertificateTag);
        } finally {
            entityManager.close();
        }
        return giftCertificateTag;
    }

}
