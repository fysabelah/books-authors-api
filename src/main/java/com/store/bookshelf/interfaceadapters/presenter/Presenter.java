package com.store.bookshelf.interfaceadapters.presenter;

import com.store.bookshelf.interfaceadapters.presenter.dto.Dto;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Presenter<T extends Serializable, D extends Dto> {

    D convert(T document);

    T convert(D dto);

    default PagedResponse<D> convertEntities(Page<T> page) {
        PagedResponse<D> paged = new PagedResponse<>();

        paged.setPage(new Pagination(page.getNumber(), page.getSize(), page.getTotalPages()));

        List<D> dada = convertEntities(page.get().toList());

        paged.setData(dada);

        return paged;
    }

    default List<D> convertEntities(List<T> documents) {
        if (documents == null) {
            return Collections.emptyList();
        }

        return documents.stream().map(this::convert).toList();
    }

    default List<T> convert(List<D> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }

        List<T> documents = new ArrayList<>();

        dtos.forEach(item -> documents.add(convert(item)));

        return documents;
    }
}
