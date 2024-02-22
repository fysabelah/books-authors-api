package com.mongodb.booksauthors.util.converter;

import com.mongodb.booksauthors.util.dto.Dto;
import com.mongodb.booksauthors.util.pagination.PagedResponse;
import com.mongodb.booksauthors.util.pagination.Pagination;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Converter<T extends Serializable, D extends Dto> {

    D convert(T document);

    T convert(D dto);

    default PagedResponse<D> convertDocuments(Page<T> page) {
        PagedResponse<D> paged = new PagedResponse<>();

        paged.setPage(new Pagination(page.getNumber(), page.getSize(), page.getTotalPages()));

        List<D> dada = convertDocuments(page.get().toList());

        paged.setData(dada);

        return paged;
    }

    default List<D> convertDocuments(List<T> documents) {
        if (documents == null) {
            return Collections.emptyList();
        }

        return documents.stream().map(this::convert).toList();
    }

    default List<T> convertDto(List<D> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }

        List<T> documents = new ArrayList<>();

        dtos.forEach(item -> {
            documents.add(convert(item));
        });

        return documents;
    }
}
