package ar.com.ada.maven.root.model.dao;


import java.util.List;


public interface DAO<T> {

    List<T> findAll();

    T findById(Integer id);

    Boolean save(T t);

    Boolean update(T t, Integer id);

    Boolean delete(Integer id);
}
