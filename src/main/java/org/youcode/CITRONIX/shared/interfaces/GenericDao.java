package org.youcode.CITRONIX.shared.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T,ID> {
    T save(T entity);
    List<T> findAll();
    Optional<T> findById(ID id);
    void delete(T entity);
    void deleteById(ID id);
}
