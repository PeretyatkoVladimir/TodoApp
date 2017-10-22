package ua.artcode.todo.dao;

import ua.artcode.todo.model.Todo;

import java.util.List;

/**
 * Created by serhii on 22.10.17.
 */
public interface TodoDao {
    Todo create(Todo todo);

    List<Todo> all();

    Todo find(int id);
}
