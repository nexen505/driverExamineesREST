package com.komarmoss.model.dao;

import com.komarmoss.model.entity.Identifiable;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAO<T extends Identifiable, ID extends Serializable> {

    T getItemById(final ID id);

    List<T> getAllItems();

    void saveItem(T entity);

    void saveOrUpdateItem(T entity);

    void updateItem(T entity);

    void removeItem(T entity);

    void removeItemById(final ID id);
}
