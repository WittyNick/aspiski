package by.gomselmash.aspiski.model.dao;

import java.util.List;

public interface GenericDao<T> {
    void create(T entity);
    void update(T entity);
    List<T> readAll();
    T readById(int id);
    void delete(int id);
}
