package com.personal.todo.services;

import com.personal.todo.dao.TodoDaoRepository;
import com.personal.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TodoService {

    public Todo createTodo(Todo todo);

    public List<Todo> getAllTodos();

    public Todo getTodo(int todoId);

    public Todo updateTodo(int todoId, Todo todo);

    public void deleteTodo(int todoId);

    public void saveAll(List<Todo> todos);
}

