package kr.or.connect.todo.api;

import kr.or.connect.todo.domain.Todo;
import static kr.or.connect.todo.domain.TodoStatus.*;
import kr.or.connect.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by ODOL on 2017. 5. 30..
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{status}")
    Collection<Todo> readTodosByStatus(@PathVariable Integer status) {
        return todoService.findByStatus(status);
    }

    @GetMapping("/count")
    public Integer countActiveTodos() {
        return todoService.findByStatus(ACTIVE_TODO.getCode()).size();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Todo create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Integer update(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        todoService.deleteById(id);
    }

    @DeleteMapping("/status/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByStatus(@PathVariable Integer status) { todoService.deleteByStatus(status); }


}
