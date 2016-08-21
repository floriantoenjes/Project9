package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.exc.DaoException;
import com.teamtreehouse.techdegrees.model.Todo;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTodoDao implements TodoDao {

    private final Sql2o sql2o;

    public Sql2oTodoDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Todo add(Todo todo) throws DaoException {
        String sql = "INSERT INTO todos(name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(todo)
                    .executeUpdate()
                    .getKey();
            todo.setId(id);
        } catch (Sql2oException ex) {
            throw new DaoException(ex, "Problem adding course");
        }

        return todo;
    }

    @Override
    public List<Todo> findAll() {
        String sql = "SELECT * FROM todos";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Todo.class);
        }
    }

    @Override
    public Todo findById(int id) {
        String sql = "SELECT * FROM todos WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id", id)
                    .executeAndFetchFirst(Todo.class);
        }
    }

    @Override
    public Todo update(Todo todo) {
        String sql = "UPDATE todos SET name = :name WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .bind(todo)
                    .executeUpdate();
            return todo;
        }
    }
}
