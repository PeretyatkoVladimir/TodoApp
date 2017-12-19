package ua.artcode.todo.service;

import ua.artcode.todo.dao.TodoDao;
import ua.artcode.todo.model.Todo;

import java.util.List;

public class MainServiceImpl implements MainService {

    private TodoDao todoDao;

    public MainServiceImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public Todo create(Todo todo) {

        if(todo == null){
            return null;
        }

        return todoDao.create(todo);
    }

    @Override
    public void initData() {
        todoDao.create(new Todo(1, "", "", false));
        todoDao.create(new Todo(2, "", "", false));
        todoDao.create(new Todo(3, "", "", false));
    }

    @Override
    public List<Todo> getAll() {
        return todoDao.all();
    }

    @Override
    public Todo find(int id) {
        return todoDao.find(id);
    }
}
