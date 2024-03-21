package com.store.bookshelf.frameworks.web;

import com.store.bookshelf.interfaceadapters.controllers.AuthorController;
import com.store.bookshelf.interfaceadapters.presenter.dto.AuthorDto;
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
@RequestMapping(value = "/author")
@Tag(name = "Autor", description = "Métodos para autores")
public class AuthorWeb {

    @Resource
    private AuthorController controller;

    @PostMapping
    @Operation(summary = "Cadastrar autores")
    public ResponseEntity<AuthorDto> insert(@Valid @RequestBody AuthorDto author) {
        return ResponseEntity.ok(controller.insert(author));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar autor")
    public ResponseEntity<AuthorDto> update(@PathVariable Integer id,
                                            @Parameter(description = "Novo nome", example = "joão") @RequestParam(required = false) String name,
                                            @Parameter(description = "Nova data de nascimento") @RequestParam(required = false) String birthdate) throws ValidationsException {
        return ResponseEntity.ok(controller.update(id, name, birthdate));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Recuperar autor por id")
    public ResponseEntity<AuthorDto> findById(@PathVariable Integer id) throws ValidationsException {
        return ResponseEntity.ok(controller.findById(id));
    }

    @GetMapping
    @Operation(summary = "Resultado ordenado por nome")
    public ResponseEntity<PagedResponse<AuthorDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                            @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage) {
        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(controller.findAll(page));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta autor")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ValidationsException {
        controller.delete(id);

        return ResponseEntity.ok().build();
    }
}
