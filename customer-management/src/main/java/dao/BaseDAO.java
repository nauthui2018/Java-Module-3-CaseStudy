package dao;

import java.util.List;

public interface BaseDAO<T> {

    public List<T> findAll();

    public void add(T t);

    public boolean delete(T t);

    public boolean update(T t);

    public T selectById(int id);
}
