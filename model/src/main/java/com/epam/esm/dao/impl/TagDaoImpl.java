package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.specification.OrderSpecification;
import com.epam.esm.specification.PaginationSpecification;
import com.epam.esm.specification.impl.OrderSpecificationImpl;
import com.epam.esm.specification.impl.PaginationSpecificationImpl;
import com.epam.esm.specification.impl.SearchTagByNameSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {

    private static final String ID_USER_PARAMETER = "idUser";

    private static final String QUERY_MOST_USED_TAG = "select tag.id, tag.name from " +
            "public.user inner join public.order on public.user.id = public.order.id_user " +
            "            inner join order_gift_certificate" +
            "            on public.order.id = order_gift_certificate.id_order inner join gift_certificate " +
            "on gift_certificate.id =  order_gift_certificate.id_gift_certificate inner join gift_certificate_tag on " +
            "            gift_certificate.id=gift_certificate_tag.id_gift_certificate inner join tag on gift_certificate_tag.id_tag=tag.id " +
            "            where public.order.id_user=(:idUser) group by tag.id,tag.name order by sum(cost) desc limit 1;";

    @PersistenceUnit(unitName = "my_persistence_unit")
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "my_persistence_unit")
    private final EntityManager entityManager;

    @Autowired
    public TagDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Tag> findByParameters(QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = query.from(Tag.class);
        CriteriaQuery<Tag> all = query.select(tagRoot);

        List<Predicate> predicates = new ArrayList<>();
        if (parameters.getSearchValue() != null) {
            parameters.getSearchValue().forEach(searchValue -> {
                predicates.add(new SearchTagByNameSpecification(searchValue).createPredicate(tagRoot, builder));
            });
        }
        Predicate search = builder.and(predicates.toArray(new Predicate[0]));
        all.where(search);

        OrderSpecification<Tag> orderSpecification = new OrderSpecificationImpl<Tag>(
                parameters.getSortValue(),
                parameters.getSortType());

        all.orderBy(orderSpecification.createOrder(tagRoot, builder));

        TypedQuery<Tag> typedQuery = entityManager.createQuery(all);

        PaginationSpecification<Tag> paginationSpecification = new PaginationSpecificationImpl<Tag>(typedQuery, parameters);

        typedQuery = paginationSpecification.createPaginationTypedQuery();

        return typedQuery.getResultList();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        Tag foundTag = entityManager.find(Tag.class, id);
        return Optional.ofNullable(foundTag);
    }

    @Override
    public Long deleteById(Long id) {
        Tag tag = entityManager.find(Tag.class, id);
        if (tag != null) {
            if (tag.getGiftCertificateTags() != null) {
                tag.getGiftCertificateTags().forEach(entityManager::remove);
            }
            entityManager.remove(tag);
            return id;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Tag save(Tag tag) {
        try {
            entityManager.persist(tag);
        } finally {
            entityManager.close();
        }
        return tag;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = query.from(Tag.class);
        CriteriaQuery<Tag> allQuery = query.select(tagRoot);

        Predicate searchByTagName = new SearchTagByNameSpecification(name).createPredicate(tagRoot, builder);
        allQuery.where(searchByTagName);

        TypedQuery<Tag> typedQuery = entityManager.createQuery(allQuery);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public Tag findMostUsedTag(Long idUser) {
        try {
            return (Tag) entityManager.createNativeQuery(QUERY_MOST_USED_TAG, Tag.class)
                    .setParameter(ID_USER_PARAMETER, idUser)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new TagNotFoundException();
        }
    }

    @Override
    public long count() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Tag> tagRoot = criteriaQuery.from(Tag.class);

        criteriaQuery.select(builder.count(tagRoot));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
