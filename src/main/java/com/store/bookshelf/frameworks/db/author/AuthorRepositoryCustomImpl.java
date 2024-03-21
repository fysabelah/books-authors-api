package com.store.bookshelf.frameworks.db.author;

import com.store.bookshelf.entities.Author;
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

class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Author>> findAllAuthorsOfBook(Integer bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        SqmPluralValuedSimplePath aliasBook = (SqmPluralValuedSimplePath) authorRoot.get("books").alias("book");

        Predicate equalBook = criteriaBuilder.equal(aliasBook.get("id"), bookId);

        criteriaQuery.select(authorRoot)
                .where(equalBook);

        Query query = entityManager.createQuery(criteriaQuery);

        List<Author> authors = query.getResultList();

        if (authors == null || authors.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(authors);
    }
}
