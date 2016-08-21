package com.teamtreehouse.techdegrees;


import com.google.gson.Gson;
import com.teamtreehouse.techdegrees.dao.Sql2oTodoDao;
import com.teamtreehouse.techdegrees.dao.TodoDao;
import com.teamtreehouse.techdegrees.model.Todo;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");

        String datasource = "jdbc:h2:./data/todos.db";

        Sql2o sql2o = new Sql2o(
                String.format("%s;INIT=RUNSCRIPT from 'classpath:db/init.sql'", datasource),
                "", "");
        TodoDao dao = new Sql2oTodoDao(sql2o);
        Gson gson = new Gson();

        get("/api/v1/todos", "application/json",(req, res) -> dao.findAll(), gson::toJson);

        post("/api/v1/todos", (req, res) -> {
            Todo todo = gson.fromJson(req.body(), Todo.class);
            dao.add(todo);
            res.status(201);
            return todo;
        }, gson::toJson);

        put("api/v1/todos/:id", "application/json", (req, res) -> {
            Todo todo = gson.fromJson(req.body(), Todo.class);
            todo = dao.update(todo);
            res.status(204);
            return todo;
        }, gson::toJson);

        delete("/api/v1", (req, res) -> "Hello!");

    }

}
