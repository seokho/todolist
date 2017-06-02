package kr.or.connect.todo.service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ODOL on 2017. 5. 30..
 */
@Service
public class TodoService {
    private TodoDao dao;

    public TodoService(TodoDao dao) {
        this.dao = dao;
    }

    public Todo create(Todo todo) {
        Integer id = dao.insert(todo);
        todo.setId(id);
        return todo;
    }

    public Todo findById(Integer id) {
        return dao.selectById(id);
    }

    public List<Todo> findByTask(String task) {
        return dao.selectByTask(task);
    }

    public List<Todo> findAll() {
        return dao.selectAll();
    }

    public Integer insert(Todo todo) {
        return dao.insert(todo);
    }

    public Integer update(Todo todo) {
        return dao.update(todo);
    }

    public Integer delete(Integer id) {
        return dao.deleteByClick(id);
    }

    public Integer count() { return dao.countTodo(); }
}
