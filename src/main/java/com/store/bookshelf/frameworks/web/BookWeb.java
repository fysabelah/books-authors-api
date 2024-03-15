package com.store.bookshelf.frameworks.web;

import com.store.bookshelf.interfaceadapters.controllers.BookController;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookDto;
import com.store.bookshelf.util.enums.Genre;
import com.store.bookshelf.util.exceptions.ValidationsException;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
@Tag(name = "Books", description = "Methods for books")
public class BookWeb {

    @Resource
    private BookController controller;

    @PostMapping
    public ResponseEntity<BookDto> insert(@Valid @RequestBody BookDto book) throws ValidationsException {
        return ResponseEntity.ok(controller.insert(book));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDto> update(@PathVariable String id,
                                          @RequestParam String title,
                                          @Parameter(description = "New genre") @RequestParam(required = false) Genre genre,
                                          @Parameter(description = "New page quantity. The value should be greater than 0.", example = "5") @RequestParam(required = false) Integer pages) throws ValidationsException {
        return ResponseEntity.ok(controller.update(id, title, genre, pages));
    }

    @PutMapping(value = "/{id}/author/{authorId}")
    public ResponseEntity<BookDto> removeAuthor(@PathVariable String id,
                                                @PathVariable String authorId) throws ValidationsException {
        return ResponseEntity.ok(controller.removeAuthor(id, authorId));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable String id) throws ValidationsException {
        return ResponseEntity.ok(controller.findById(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<BookDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                          @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage,
                                                          @Parameter(description = "Title of the book", example = "The Little Prince") @RequestParam(required = false) String title) {
        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(controller.findAll(page, title));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws ValidationsException {
        controller.delete(id);

        return ResponseEntity.ok().build();
    }
}