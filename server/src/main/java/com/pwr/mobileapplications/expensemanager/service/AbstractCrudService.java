package com.pwr.mobileapplications.expensemanager.service;

import java.util.List;

public interface AbstractCrudService<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T save(T element);
    boolean deleteById(ID id);
}
