package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.frameworks.db.publisher.PublisherRepository;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherGateway extends GenericGateway<Publisher> {

    @Resource
    private PublisherRepository repository;

    public Publisher insert(Publisher entity) {
        return super.insert(entity, repository);
    }

    public Publisher findById(Integer id) throws ValidationsException {
        return super.findById(id, repository);
    }

    public Publisher update(Publisher entity) {
        return super.update(entity, repository);
    }

    public void delete(Publisher entity) {
        super.delete(entity, repository);
    }

    public Page<Publisher> findAll(Pageable page) {
        return super.findAll(page, repository);
    }

    public List<Publisher> findAllById(List<Integer> publishers) {
        if (publishers == null || publishers.isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Editor"));
        }

        return repository.findAllById(publishers);
    }

    public List<Publisher> addBook(List<Publisher> publishers, Book book) {
        publishers.forEach(publisher -> publisher.addBook(book));

        return repository.saveAll(publishers);
    }

    public List<Publisher> findByBookId(Integer bookId) throws ValidationsException {
        return repository.findAllByBooksId(bookId)
                .orElseThrow(() -> new ValidationsException("0007", "editores", bookId.toString()));
    }

    public List<Publisher> findAllPublishersOfBook(Integer bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        return repository.findAllPublishersOfBook(bookId)
                .orElse(new ArrayList<>());
    }

    public void removeBookFromPublisher(Integer publisherId, Book book) throws ValidationsException {
        if (publisherId == null || book == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        Publisher publisher = findById(publisherId);

        publisher.getBooks().remove(book);

        repository.save(publisher);
    }
}