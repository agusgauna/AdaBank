package ar.com.ada.maven.root.model.dao;

import java.util.Collection;


public interface DAO<T> {
    Collection<T> findAll();

    T findById(Integer id);

    ContactDAO findById(String mail);

    Boolean save(T t);

    Boolean update(T t, Integer id);

    Boolean delete(Integer id);

    Boolean update(char mail);
}
