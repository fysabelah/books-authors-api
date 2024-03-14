package com.store.bookshelf.interfaceadapters.presenter;

import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.interfaceadapters.presenter.dto.PublisherDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class PublisherPresenter implements Presenter<Publisher, PublisherDto> {

    @Resource
    private BookPresenter presenter;

    @Override
    public PublisherDto convert(Publisher entity) {
        PublisherDto dto = new PublisherDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBooks(presenter.convertEntities(entity.getBooks()));

        return dto;
    }

    @Override
    public Publisher convert(PublisherDto dto) {
        Publisher publisher = new Publisher();

        publisher.setBooks(presenter.convert(dto.getBooks()));
        publisher.setId(dto.getId());
        publisher.setName(dto.getName());

        return publisher;
    }
}
