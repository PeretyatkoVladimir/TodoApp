package ua.artcode.todo.service;

import ua.artcode.todo.model.Todo;

import java.util.List;

public interface MainService {

    Todo create(Todo todo);

    void initData();

    List<Todo> getAll();

    Todo find(int id);

    boolean remove(int id);

}
