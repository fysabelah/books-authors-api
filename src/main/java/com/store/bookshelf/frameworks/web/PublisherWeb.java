package com.store.bookshelf.frameworks.web;

import com.store.bookshelf.interfaceadapters.controllers.PublisherController;
import com.store.bookshelf.interfaceadapters.presenter.dto.PublisherDto;
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
@RequestMapping(value = "/publisher")
@Tag(name = "Publisher", description = "Methods for publishers")
public class PublisherWeb {

    @Resource
    private PublisherController controller;

    @PostMapping
    public ResponseEntity<PublisherDto> insert(@Valid @RequestBody PublisherDto publisher) {
        return ResponseEntity.ok(controller.insert(publisher));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PublisherDto> update(@PathVariable Integer id,
                                               @Parameter(description = "New name", example = "Darkside") @RequestParam String name) throws ValidationsException {
        return ResponseEntity.ok(controller.update(id, name));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PublisherDto> findById(@PathVariable Integer id) throws ValidationsException {
        return ResponseEntity.ok(controller.findById(id));
    }

    @GetMapping
    @Operation(summary = "The result is sort by name")
    public ResponseEntity<PagedResponse<PublisherDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                               @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage) {
        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(controller.findAll(page));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ValidationsException {
        controller.delete(id);

        return ResponseEntity.ok().build();
    }
}
