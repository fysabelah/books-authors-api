package com.store.bookshelf.frameworks.web;

import com.store.bookshelf.interfaceadapters.controllers.BookController;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookCreateDto;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookDto;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookFullDto;
import com.store.bookshelf.util.enums.Genre;
import com.store.bookshelf.util.exceptions.ValidationsException;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
@Tag(name = "Livros", description = "Método para livros")
public class BookWeb {

    @Resource
    private BookController controller;

    @PostMapping
    @Operation(summary = "Inserir livro")
    public ResponseEntity<BookDto> insert(@Valid @RequestBody BookCreateDto book) throws ValidationsException {
        return ResponseEntity.ok(controller.insert(book));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar livros")
    public ResponseEntity<BookDto> update(@PathVariable Integer id,
                                          @RequestParam(required = false) String title,
                                          @Parameter(description = "New genre") @RequestParam(required = false) Genre genre,
                                          @Parameter(description = "New page quantity. The value should be greater than 0.", example = "5") @RequestParam(required = false) Integer pages) throws ValidationsException {
        return ResponseEntity.ok(controller.update(id, title, genre, pages));
    }

    @PutMapping(value = "/{id}/author/{authorId}")
    @Operation(summary = "Remover autor de um livro")
    public ResponseEntity<Void> removeAuthor(@PathVariable Integer id,
                                             @PathVariable Integer authorId) throws ValidationsException {
        controller.removeAuthor(id, authorId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/publisher/{publisherId}")
    @Operation(summary = "Remover editora de um livro")
    public ResponseEntity<Void> removePublisher(@PathVariable Integer id,
                                                @PathVariable Integer publisherId) throws ValidationsException {
        controller.removePublisher(id, publisherId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar livro por id")
    public ResponseEntity<BookDto> findById(@PathVariable Integer id) throws ValidationsException {
        return ResponseEntity.ok(controller.findById(id));
    }

    @GetMapping(value = "/full/{id}")
    @Operation(summary = "Buscar livro completo por id")
    public ResponseEntity<BookFullDto> findBookById(@PathVariable Integer id) throws ValidationsException {
        return ResponseEntity.ok(controller.findBookById(id));
    }

    @GetMapping
    @Operation(summary = "Buscar livros com resultado paginado")
    public ResponseEntity<PagedResponse<BookDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                          @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage,
                                                          @Parameter(description = "Title of the book", example = "The Little Prince") @RequestParam(required = false) String title) {
        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(controller.findAll(page, title));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar livro")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ValidationsException {
        controller.delete(id);

        return ResponseEntity.ok().build();
    }
}
