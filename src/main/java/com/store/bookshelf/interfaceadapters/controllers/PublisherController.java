package com.store.bookshelf.interfaceadapters.controllers;

import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.interfaceadapters.gateway.BookGateway;
import com.store.bookshelf.interfaceadapters.gateway.PublisherGateway;
import com.store.bookshelf.interfaceadapters.presenter.PublisherPresenter;
import com.store.bookshelf.interfaceadapters.presenter.dto.PublisherDto;
import com.store.bookshelf.usercase.PublisherBusiness;
import com.store.bookshelf.util.exceptions.ValidationsException;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PublisherController {

    @Resource
    private PublisherGateway gateway;

    @Resource
    private PublisherPresenter presenter;

    @Resource
    private PublisherBusiness business;

    @Resource
    private BookGateway bookGateway;

    public PublisherDto insert(PublisherDto dto) {
        Publisher publisher = presenter.convert(dto);

        publisher = gateway.insert(publisher);

        return presenter.convert(publisher);
    }

    public PublisherDto findById(Integer id) throws ValidationsException {
        Publisher publisher = getPublisher(id);

        return presenter.convert(publisher);
    }

    public PagedResponse<PublisherDto> findAll(Pagination pagination) {
        Pageable page = PageRequest.of(pagination.getPage(),
                pagination.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name"));

        Page<Publisher> publishers = gateway.findAll(page);

        return presenter.convertEntities(publishers);
    }

    public void delete(Integer id) throws ValidationsException {
        Publisher publisher = getPublisher(id);

        business.delete(publisher);

        gateway.delete(publisher);
    }

    public PublisherDto update(Integer id, String newName) throws ValidationsException {
        Publisher publisher = getPublisher(id);

        publisher = business.update(newName, publisher);

        publisher = gateway.update(publisher);

        return presenter.convert(publisher);
    }

    private Publisher getPublisher(Integer id) throws ValidationsException {
        return gateway.findById(id);
    }
}
