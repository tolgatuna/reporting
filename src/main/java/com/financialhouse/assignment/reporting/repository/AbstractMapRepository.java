package com.financialhouse.assignment.reporting.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapRepository<T> {
    protected Map<String, T> map;

    public AbstractMapRepository() {
        map = new HashMap<>();
    }

    public List<T> listAll() {
        return new ArrayList<>(map.values());
    }

    public T getById(String id) {
        return map.get(id);
    }

    public T saveOrUpdate(String id, T object) {
        if (object != null) {
            map.put(id, object);
            return object;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(String id) {
        map.remove(id);
    }

    public void deleteAll() {
        map.clear();
    }
}
