package kr.or.connect.todo.presentation;

import kr.or.connect.todo.domain.Todo;
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
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;
    private final Logger log = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    Collection<Todo> readList() {
        return todoService.findAll();
    }

    @GetMapping("/{task}")
    Collection<Todo> readByTask(@PathVariable String task) {
        return todoService.findByTask(task);
    }

    @GetMapping("/{id}")
    Todo readById(@PathVariable Integer id) {
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Todo create(@RequestBody Todo todo) {
        Todo newTodo = todoService.create(todo);
        log.info("todo created : {}", newTodo);
        return todo;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable Integer id, @RequestBody Todo todo) {
        todo.setId(id);
        todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        todoService.delete(id);
    }

}
