package com.codecool.todo.service;

import com.codecool.todo.model.Status;
import com.codecool.todo.model.Todo;
import com.codecool.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    public void save(Todo todo) {
        repo.save(todo);
    }

    public Todo find(Long id) {
        return repo.findOne(id);
    }

    public void update(Long id, String title) {
        Todo todo = find(id);
        todo.setTitle(title);
        save(todo);
    }

    public List<Todo> ofStatus(String statusString) {
        return (statusString == null || statusString.isEmpty()) ? repo.findAll() : ofStatus(Status.valueOf(statusString.toUpperCase()));
    }

    public List<Todo> ofStatus(Status status) {
        return repo.findAllByStatus(status);
    }

    public void remove(Long id) {
        repo.delete(id);
    }

    public void removeCompleted() {
        repo.removeAllByStatus(Status.COMPLETE);
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Todo todo = find(id);
        if (isComplete) {
            todo.setStatus(Status.COMPLETE);
        } else {
            todo.setStatus(Status.ACTIVE);
        }
        save(todo);
    }

    public void toggleAll(boolean complete) {
        repo.findAll().forEach(t -> {
            t.setStatus(complete ? Status.COMPLETE : Status.ACTIVE);
            save(t);
        });
    }
}
