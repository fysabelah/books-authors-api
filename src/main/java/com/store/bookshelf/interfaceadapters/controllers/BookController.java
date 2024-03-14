package com.store.bookshelf.interfaceadapters.controllers;

import com.store.bookshelf.interfaceadapters.presenter.dto.BookDto;
import com.store.bookshelf.util.enums.Genre;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class BookController {

    public BookDto insert(BookDto dto) {
        return null;
    }

    public BookDto update(String id, String title, Genre genre, Integer pages) {
        return null;
    }

    public PagedResponse<BookDto> findAll(Pagination pagination, String title) {
        Pageable page = PageRequest.of(pagination.getPage(),
                pagination.getPageSize(),
                Sort.by(Sort.Direction.ASC, "title"));

        return null;
    }

    public void delete(String id) {

    }

    public BookDto findById(String id) {
        return null;
    }

    public BookDto removeAuthor(String id, String authorId) {
        return null;
    }
}
