package com.dinhphu.services;

import java.util.List;

public interface IServices<T> {
    public List<T> findAll();
    public T findById(int id);
    public void save(T model);
    public void update(int id,T model);
    public void remove(int id);
}
