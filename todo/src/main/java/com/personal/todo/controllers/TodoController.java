package com.personal.todo.controllers;

import com.personal.todo.models.Todo;
import com.personal.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);
    Random random = new Random();

    @Autowired
    TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
        todo.setAddedDate(new Date());
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    @PostMapping("/save/all")
    public ResponseEntity<Todo> createAllTodoHandler(@RequestBody List<Todo> todos) {
        for(Todo todo:todos)
          todo.setAddedDate(new Date());
        todoService.saveAll(todos);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler() {
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) {
        Todo todo = todoService.getTodo(todoId);
        if(todo==null)return ResponseEntity.noContent()
                .build();
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todo, @PathVariable int todoId) {
        Todo todo1 = todoService.updateTodo(todoId, todo);
        return ResponseEntity.ok(todo1);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("delete");
    }

}
