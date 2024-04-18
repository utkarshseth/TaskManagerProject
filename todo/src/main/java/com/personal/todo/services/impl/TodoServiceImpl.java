package com.personal.todo.services.impl;

import com.personal.todo.dao.TodoDaoRepository;
import com.personal.todo.models.Todo;
import com.personal.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDaoRepository dao;

    Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Override
    public Todo createTodo(Todo todo) {

        logger.info("Adding Todo {} ", todo);
        dao.save(todo);
        return todo;
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> list = new ArrayList<>();
        Iterable<Todo> todos = dao.findAll();
        for (Todo todo : todos) {
            list.add(todo);
        }
        logger.info("Getting all todos :{}", list);
        return list;
    }

    @Override
    public Todo getTodo(int todoId) {
        Todo todo = dao.findById(todoId)
                .orElse(null);
        logger.info("TODO getById id:{} todo:{} ", todoId, todo);
        return todo;
    }

    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        todo.setId(todoId);
        logger.info("Updating todo:{} ", todo);
        dao.save(todo);
        return todo;
    }

    @Override
    public void deleteTodo(int todoId) {
        logger.info("Deleting id:{]", todoId);
        dao.deleteById(todoId);
    }

    @Override
    public void saveAll(List<Todo> todos) {
        logger.info("Saving all:{} ", todos);
        dao.saveAll(todos);
    }
}

