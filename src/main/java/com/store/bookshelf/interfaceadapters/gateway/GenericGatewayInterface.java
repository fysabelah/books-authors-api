package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.util.exceptions.ValidationsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericGatewayInterface<T> {

    T insert(T entity, JpaRepository<T, Integer> repository);

    T update(T entity, JpaRepository<T, Integer> repository);

    void delete(T entity, JpaRepository<T, Integer> repository);

    Page<T> findAll(Pageable page, JpaRepository<T, Integer> repository);

    T findById(Integer id, JpaRepository<T, Integer> repository) throws ValidationsException;
}
