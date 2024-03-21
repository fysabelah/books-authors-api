package com.store.bookshelf.frameworks.db.publisher;

import com.store.bookshelf.entities.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.sqm.tree.domain.SqmPluralValuedSimplePath;

import java.util.List;
import java.util.Optional;

class PublisherRepositoryCustomImpl implements PublisherRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Publisher>> findAllPublishersOfBook(Integer bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Publisher> criteriaQuery = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> publisherRoot = criteriaQuery.from(Publisher.class);
        SqmPluralValuedSimplePath aliasBook = (SqmPluralValuedSimplePath) publisherRoot.get("books").alias("book");

        Predicate equalBook = criteriaBuilder.equal(aliasBook.get("id"), bookId);

        criteriaQuery.select(publisherRoot)
                .where(equalBook);

        Query query = entityManager.createQuery(criteriaQuery);

        List<Publisher> publishers = query.getResultList();

        if (publishers == null || publishers.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(publishers);
    }
}
