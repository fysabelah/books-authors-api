package com.store.bookshelf.interfaceadapters.presenter;

import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.interfaceadapters.presenter.dto.PublisherDto;
import org.springframework.stereotype.Component;

@Component
public class PublisherPresenter implements Presenter<Publisher, PublisherDto> {

    @Override
    public PublisherDto convert(Publisher entity) {
        PublisherDto dto = new PublisherDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    @Override
    public Publisher convert(PublisherDto dto) {
        Publisher publisher = new Publisher();

        publisher.setId(dto.getId());
        publisher.setName(dto.getName());

        return publisher;
    }
}
