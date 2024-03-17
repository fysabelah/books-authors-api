package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.frameworks.db.PublisherRepository;
import com.store.bookshelf.util.exceptions.ValidationsException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}