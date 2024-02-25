package com.mongodb.booksauthors.controllers;

import com.mongodb.booksauthors.services.AuthorService;
import com.mongodb.booksauthors.util.dto.AuthorDto;
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
@RequestMapping(value = "/authors")
@Tag(name = "Authors", description = "Methods for authors")
public class AuthorController {

    @Resource
    private AuthorService service;

    @PostMapping
    public ResponseEntity<AuthorDto> insert(@Valid @RequestBody AuthorDto author) {
        return ResponseEntity.ok(service.insert(author));
    }

    @PutMapping
    public ResponseEntity<AuthorDto> update(@Valid @RequestBody AuthorDto author) throws ValidationsException {
        return ResponseEntity.ok(service.update(author));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable String id) throws ValidationsException {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<AuthorDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                          @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage) {
        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(service.findAll(page));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws ValidationsException {
        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
