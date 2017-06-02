package kr.or.connect.todo.persistence;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import kr.or.connect.todo.domain.Todo;
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

    @Test
    public void shouldCount() {
        Integer count = dao.countTodo();
        assertThat(count, is(notNullValue()));
    }

    @Test
    public void shouldSelectAll() {
        List<Todo> allTodo = dao.selectAll();
        assertThat(allTodo, is(notNullValue()));
    }
}
