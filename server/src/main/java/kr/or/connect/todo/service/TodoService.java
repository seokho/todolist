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

    public List<Todo> findByStatus(String status) {
        return dao.selectByStatus(status);
    }

    public Todo create(Todo todo) {
        return dao.create(todo);
    }

    public Integer update(Todo todo) {
        todo.setModified(new Date());
        return dao.update(todo);
    }

    public Integer deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
