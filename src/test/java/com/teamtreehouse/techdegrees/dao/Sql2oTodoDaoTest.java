package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.model.Todo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oTodoDaoTest {
    private Sql2oTodoDao dao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        dao = new Sql2oTodoDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingTodoSetsId() throws Exception {
        Todo todo = new Todo("Work on this project");
        int originalId = todo.getId();

        dao.add(todo);

        assertNotEquals(originalId, todo.getId());

    }

    @Test
    public void findAllReturnsTodosAfterAdding() throws Exception {
        dao.add(new Todo("Second Todo"));
        dao.add(new Todo("First Todo"));

        assertEquals(2, dao.findAll().size());
    }

    @Test
    public void findByIdReturnsTodo() throws Exception {
        Todo todo = new Todo("Todo");
        dao.add(todo);

        assertEquals(todo, dao.findById(1));
    }

}