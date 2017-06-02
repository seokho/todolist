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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	public List<Todo> selectByStatus(String status) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		return jdbc.query(SELECT_BY_STATUS, params, rowMapper);
	}

	public List<Todo> selectAll() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}

	public Todo insert(Todo todo) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbc.update(INSERT_TODO, params, holder, new String[] {"id"});

		Number generatedId = holder.getKey();
		long id = generatedId.intValue();

		Map<String, Object> params1 = new HashMap<>();
		params1.put("id", id);

		return jdbc.queryForObject(SELECT_BY_ID, params1, BeanPropertyRowMapper.newInstance(Todo.class));
	}

	public Todo update(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.queryForObject(UPDATE_BY_ID, params, rowMapper);
	}

	public Integer deleteById(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(DELETE_BY_ID, params);
	}

}
