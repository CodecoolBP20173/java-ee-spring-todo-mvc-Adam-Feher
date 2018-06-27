package com.codecool.todo.model;

import javax.persistence.*;

@Entity
@Table(name = "TODO")
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private Status status;

    public Todo(String title, Status status) {
        this.title = title;
        this.status = status;
    }

    public Todo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public static Todo create(String title) {
        return new Todo(title, Status.ACTIVE);
    }

}
