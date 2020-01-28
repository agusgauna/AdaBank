package ar.com.ada.maven.root.model.dao;

import java.util.Collection;

public interface DAO {
    public interface DAO <T>{
        Collection<T> findAll();

        T findById(Integer id);

        Boolean save(T t);

        Boolean update(T t, Integer id);

        Boolean delete(Integer id);
}
