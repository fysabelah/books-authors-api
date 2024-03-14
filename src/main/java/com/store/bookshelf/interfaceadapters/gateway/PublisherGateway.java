package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.frameworks.db.PublisherRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PublisherGateway {

    @Resource
    private PublisherRepository repository;
}