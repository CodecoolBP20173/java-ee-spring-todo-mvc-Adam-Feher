package com.codecool.todo.service;
import com.codecool.todo.model.Todo;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    public Initializer(TodoService todoService) {
        todoService.save(Todo.create("first TODO item"));
        todoService.save(Todo.create("second TODO item"));
        todoService.save(Todo.create("third TODO item"));
    }
}
