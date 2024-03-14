package com.store.bookshelf.interfaceadapters.controllers;

import com.store.bookshelf.interfaceadapters.presenter.dto.PublisherDto;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import org.springframework.stereotype.Component;

@Component
public class PublisherController {

    public PublisherDto insert(PublisherDto dto) {
        return null;
    }

    public PublisherDto update(PublisherDto dto) {
        return null;
    }

    public PublisherDto findById(String id) {
        return null;
    }

    public PagedResponse<PublisherDto> findAll(Pagination page) {
        return null;
    }

    public void delete(String id) {

    }
}
