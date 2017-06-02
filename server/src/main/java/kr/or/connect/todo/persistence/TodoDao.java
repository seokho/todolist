package kr.or.connect.todo.persistence;

import javax.sql.DataSource;
import static kr.or.connect.todo.persistence.TodoSqls.*;

import kr.or.connect.todo.domain.Todo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);

	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");

	}

	public Integer countTodo( ) {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(COUNT_TODO, params, Integer.class);
	}

	public Todo selectById(Integer id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(SELECT_BY_ID, params, rowMapper);
	}

	public List<Todo> selectByTask(String task) {
		Map<String, Object> params = new HashMap<>();
		params.put("task", task);
		return jdbc.query(SELECT_BY_TASK, params, rowMapper);
	}

	public List<Todo> selectAll() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}

	public Integer insert(Todo todo) {
        System.out.println(todo);
        SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
        return jdbc.update(INSERT_TODO, params);
//        return insertAction.executeAndReturnKey(params).intValue();
	}

	public Integer update(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(UPDATE_BY_ID, params);
	}

	public Integer deleteByClick(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(DELETE_BY_ID, params);
	}

}
