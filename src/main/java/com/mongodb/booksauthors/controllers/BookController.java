package com.mongodb.booksauthors.controllers;

import com.mongodb.booksauthors.services.BookService;
import com.mongodb.booksauthors.util.dto.BookDto;
import com.mongodb.booksauthors.util.exceptions.ValidationsException;
import com.mongodb.booksauthors.util.pagination.PagedResponse;
import com.mongodb.booksauthors.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
@Tag(name = "Books", description = "Methods for books")
public class BookController {

    @Resource
    private BookService service;

    @PostMapping
    public ResponseEntity<BookDto> insert(@Valid @RequestBody BookDto book) {
        return ResponseEntity.ok(service.insert(book));
    }

    @PutMapping
    public ResponseEntity<BookDto> update(@Valid @RequestBody BookDto book) throws ValidationsException {
        return ResponseEntity.ok(service.update(book));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable String id) throws ValidationsException {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<BookDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                          @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage,
                                                          @Parameter(description = "Title of the book", example = "The Little Prince") @RequestParam(required = false) String title) {
        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(service.findAll(page, title));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws ValidationsException {
        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
