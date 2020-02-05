package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.dto.Account_type;

import java.util.List;


public interface DAO<T> {
    List<Account_type> findAll();

    T findById(Integer id);

    Boolean save(T t);

    Boolean update(T t, Integer id);

    Boolean delete(Integer id);
}
