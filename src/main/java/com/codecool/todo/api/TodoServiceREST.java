package com.codecool.todo.api;

import com.codecool.todo.model.Todo;
import com.codecool.todo.repository.TodoRepository;
import com.codecool.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoServiceREST {
    TodoRepository repo;
    TodoService todoService;

    public TodoServiceREST(TodoRepository repo, TodoService todoService) {
        this.repo = repo;
        this.todoService = todoService;
    }

    // Add new
    @PostMapping("/addTodo")
    public ResponseEntity addTodo(@RequestParam("todo-title") String title) {
        Todo newTodo = Todo.create(title);
        repo.save(newTodo);
        return new ResponseEntity(HttpStatus.OK);
    }

    // List by id
    @PostMapping("/list")
    public ResponseEntity<List<Todo>> listAll(@RequestParam("status") String status) {
        List<Todo> todos = todoService.ofStatus(status);
        return new ResponseEntity(todos, HttpStatus.OK);
    }


    // Remove all completed
    @DeleteMapping("/todos/completed")
    public ResponseEntity removeCompleted() {
        todoService.removeCompleted();
        return new ResponseEntity(HttpStatus.OK);
    }


    // Toggle all status
    @PutMapping("/todos/toggle_all")
    public ResponseEntity toggleAllStatus(@RequestParam("toggle-all") String status) {
        todoService.toggleAll(status.equals("true"));
        return new ResponseEntity(HttpStatus.OK);
    }

    // Remove by id
    @DeleteMapping("/todos/{id}")
    public ResponseEntity removeById(@PathVariable("id") String id) {
        todoService.remove(Long.decode(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    // Update by id
    @PutMapping("/todos/{id}")
    public ResponseEntity updateById(@PathVariable("id") String id, @RequestParam("todo-title") String title) {
        todoService.update(Long.decode(id),title);
        return new ResponseEntity(HttpStatus.OK);
    }
    // Find by id
    @GetMapping("todos/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") String id) {
        String title = todoService.find(Long.decode(id)).getTitle();
        return new ResponseEntity(title,HttpStatus.OK);
    }

    // Toggle status by id
    @PutMapping("/todos/{id}/toggle_status")
    public ResponseEntity toggleStatusById(@PathVariable("id") String id, @RequestParam("status") String status) {
        todoService.toggleStatus(Long.decode(id),status.equals("true"));
        return new ResponseEntity(HttpStatus.OK);
    }
}
