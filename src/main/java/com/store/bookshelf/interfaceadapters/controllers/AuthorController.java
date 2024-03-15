package com.store.bookshelf.interfaceadapters.controllers;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.interfaceadapters.gateway.AuthorGateway;
import com.store.bookshelf.interfaceadapters.gateway.BookGateway;
import com.store.bookshelf.interfaceadapters.presenter.AuthorPresenter;
import com.store.bookshelf.interfaceadapters.presenter.dto.AuthorDto;
import com.store.bookshelf.usercase.AuthorBusiness;
import com.store.bookshelf.util.exceptions.ValidationsException;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorController {

    @Resource
    private AuthorGateway gateway;

    @Resource
    private AuthorPresenter conversor;

    @Resource
    private AuthorBusiness business;

    @Resource
    private BookGateway bookGateway;

    public AuthorDto insert(AuthorDto dto) {
        Author author = conversor.convert(dto);

        author = gateway.insert(author);

        return conversor.convert(author);
    }

    public AuthorDto findById(Integer id) throws ValidationsException {
        Author author = gateway.findById(id);

        return conversor.convert(author);
    }

    public PagedResponse<AuthorDto> findAll(Pagination pagination) {
        Pageable page = PageRequest.of(pagination.getPage(),
                pagination.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name"));

        Page<Author> authors = gateway.findAll(page);

        return conversor.convertEntities(authors);
    }

    public void delete(Integer id) throws ValidationsException {
        Author author = gateway.findById(id);
        List<Book> books = bookGateway.findAllByAuthorsId(id);

        business.delete(author, books);

        gateway.delete(author);
    }

    public AuthorDto update(Integer id, String name, String birthdate) throws ValidationsException {
        Author author = gateway.findById(id);

        author = business.update(author, name, birthdate);

        author = gateway.update(author);

        return conversor.convert(author);
    }
}
