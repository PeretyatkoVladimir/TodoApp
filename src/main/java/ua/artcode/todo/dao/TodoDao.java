package ua.artcode.todo.dao;

import ua.artcode.todo.model.Todo;

import java.util.List;

public interface TodoDao {
    Todo create(Todo todo);

    List<Todo> all();

    Todo find(int id);
}
