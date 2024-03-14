package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.frameworks.db.AuthorRepository;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthorGateway extends GenericGateway<Author> {

    @Resource
    private AuthorRepository repository;

    public Author findByNameLike(String name) throws ValidationsException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Nome"));
        }

        return repository.findByNameLike(name)
                .orElseThrow(() -> new ValidationsException(MessageUtil.getMessage("0200")));
    }

    public Page<Author> findByBirthdate(LocalDate date, Pageable page) {
        return repository.findByBirthdate(date, page);
    }

    public Author insert(Author entity) {
        return super.insert(entity, repository);
    }

    public Author findById(Integer id) throws ValidationsException {
        return super.findById(id, repository);
    }

    public Author update(Author entity) {
        return super.update(entity, repository);
    }

    public void delete(Author entity) {
        super.delete(entity, repository);
    }

    public Page<Author> findAll(Pageable page) {
        return super.findAll(page, repository);
    }
}
