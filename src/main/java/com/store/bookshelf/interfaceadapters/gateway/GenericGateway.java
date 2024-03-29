package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

class GenericGateway<T extends Serializable> {

    protected T insert(T entity, JpaRepository<T, Integer> repository) {
        return save(entity, repository);
    }

    protected T update(T entity, JpaRepository<T, Integer> repository) {
        return save(entity, repository);
    }

    private T save(T entity, JpaRepository<T, Integer> repository) {
        validateEntity(entity);

        return repository.save(entity);
    }

    private void validateEntity(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0002", entity.getClass().getName()));
        }
    }

    protected T findById(Integer id, JpaRepository<T, Integer> repository) throws ValidationsException {
        if (id == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Id"));
        }

        return repository.findById(id)
                .orElseThrow(() -> new ValidationsException("0005", id.toString()));
    }

    protected void delete(T entity, JpaRepository<T, Integer> repository) {
        validateEntity(entity);

        repository.delete(entity);
    }

    protected Page<T> findAll(Pageable page, JpaRepository<T, Integer> repository) {
        if (page == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "P\u00E1gina"));
        }

        return repository.findAll(page);
    }
}
