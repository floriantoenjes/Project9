package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.exc.DaoException;
import com.teamtreehouse.techdegrees.model.Todo;

import java.util.List;

public interface TodoDao {
    Todo add(Todo todo) throws DaoException;

    List<Todo> findAll();

    Todo findById(int id);

    Todo update(Todo todo);

    void delete(int id);
}
