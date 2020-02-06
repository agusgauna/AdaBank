package ar.com.ada.maven.root.model.dao;


import java.util.Collection;


public interface DAO<T> {

     // CountryDAO findById(String id);


    Collection<T> findAll();

    T findById(Integer id);

    MailDAO findById(String mail);

    Boolean save(T t);

    Boolean update(T t, Integer id);

    Boolean delete(Integer id);
}
