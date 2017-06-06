package kr.or.connect.todo.persistence;
import static kr.or.connect.todo.domain.TodoStatus.ACTIVE_TODO;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.domain.TodoStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by odol on 2017-6-1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoDaoTest {
    @Autowired
    private TodoDao dao;
    private Todo todo;

    @Before
    public void setUp() {
        todo = new Todo();
        todo.setTitle("TestBase");
        todo.setStatus(ACTIVE_TODO.getCode());
        dao.create(todo);
    }

    @Test
    public void shouldCount() {
        Integer count = dao.selectByStatus(ACTIVE_TODO.getCode()).size();
        assertThat(count, is(notNullValue()));
    }

    @Test
    public void shouldSelectAll() {
        List<Todo> allTodo = dao.selectAll();
        assertThat(allTodo, is(notNullValue()));
    }

    @Test
    public void shouldDeleteByStatus() {
       Integer updateReturnValue = dao.deleteByStatus(ACTIVE_TODO.getCode());
       assertThat(updateReturnValue, is(notNullValue()));
    }

    @Test
    public void shouldCreate() {
        Todo queryForObjectReturnValue = dao.create(todo);
        assertThat(queryForObjectReturnValue, is(notNullValue()));
    }

    @Test
    public void shouldUpdate() {
        Integer updateReturnValue = dao.update(todo);
        assertThat(updateReturnValue, is(notNullValue()));
    }
}
