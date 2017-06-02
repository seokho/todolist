package kr.or.connect.todo.service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return dao.insert(todo);
    }

    public Todo findById(Integer id) {
        return dao.selectById(id);
    }

    public List<Todo> findByStatus(String status) {
        return dao.selectByStatus(status);
    }

    public List<Todo> findAll() {
        return dao.selectAll();
    }

    public Todo update(Todo todo) {
        todo.setModified(new Date());
        return dao.update(todo);
    }

    public Integer delete(Integer id) {
        return dao.deleteById(id);
    }

    public int count() {
        return 0;
    }
}
