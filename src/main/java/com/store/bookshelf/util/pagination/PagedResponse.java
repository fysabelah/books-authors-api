package com.store.bookshelf.util.pagination;

import com.store.bookshelf.interfaceadapters.presenter.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PagedResponse<T extends Dto> {

    private Pagination page;

    private List<T> data;
}
