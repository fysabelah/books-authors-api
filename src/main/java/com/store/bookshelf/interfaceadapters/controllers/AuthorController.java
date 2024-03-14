package com.store.bookshelf.interfaceadapters.controllers;

import com.store.bookshelf.interfaceadapters.presenter.dto.AuthorDto;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class AuthorController {

    public AuthorDto insert(AuthorDto dto) {
        return null;
    }

    public AuthorDto findById(String id) {
        return null;
    }

    public PagedResponse<AuthorDto> findAll(Pagination pagination) {
        Pageable page = PageRequest.of(pagination.getPage(),
                pagination.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name"));

        return null;
    }

    public void delete(String id) {

    }

    public AuthorDto update(Integer id, String name, String birthdate) {
        return null;
    }
}
