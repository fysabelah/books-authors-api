package com.store.bookshelf.frameworks.db.books;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.sqm.tree.domain.SqmPluralValuedSimplePath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> findByBookIdAndAuthorId(Integer bookId, Integer authorId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        SqmPluralValuedSimplePath aliasBook = (SqmPluralValuedSimplePath) authorRoot.get("books").alias("book");

        Predicate[] predicates = getPredicates(
                criteriaBuilder.equal(authorRoot.get("id"), authorId),
                criteriaBuilder.equal(bookRoot.get("id"), bookId),
                criteriaBuilder.equal(aliasBook.get("id"), bookRoot.get("id"))
        );

        criteriaQuery.select(bookRoot)
                .where(predicates);

        Query query = entityManager.createQuery(criteriaQuery);

        try {
            Book book = (Book) query.getSingleResult();

            return Optional.of(book);
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> findByBookIdAndPublisherId(Integer bookId, Integer publisherId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Root<Publisher> publisherRoot = criteriaQuery.from(Publisher.class);
        SqmPluralValuedSimplePath aliasBook = (SqmPluralValuedSimplePath) publisherRoot.get("books").alias("book");

        Predicate[] predicates = getPredicates(
                criteriaBuilder.equal(publisherRoot.get("id"), publisherId),
                criteriaBuilder.equal(bookRoot.get("id"), bookId),
                criteriaBuilder.equal(aliasBook.get("id"), bookRoot.get("id"))
        );

        criteriaQuery.select(bookRoot)
                .where(predicates);

        Query query = entityManager.createQuery(criteriaQuery);

        try {
            Book book = (Book) query.getSingleResult();

            return Optional.of(book);
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }

    private Predicate[] getPredicates(Predicate condition1, Predicate condition2, Predicate join) {
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(condition1);
        predicateList.add(condition2);
        predicateList.add(join);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);

        return predicates;
    }
}
