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

	public Todo selectById(Integer id) {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("id", id);
		return jdbc.queryForObject(SELECT_BY_ID, paramsMap, rowMapper);
	}

	public List<Todo> selectByStatus(String status) {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("status", status);
		return jdbc.query(SELECT_BY_STATUS, paramsMap, rowMapper);
	}

	public List<Todo> selectAll() {
		Map<String, Object> paramsMap = Collections.emptyMap();
		return jdbc.query(SELECT_ALL, paramsMap, rowMapper);
	}

	public Todo create(Todo todo) {
        SqlParameterSource paramsSource = new BeanPropertySqlParameterSource(todo);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbc.update(INSERT_TODO, paramsSource, holder, new String[] {"id"});

		Number generatedId = holder.getKey();
		long id = generatedId.intValue();

		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("id", id);

		return jdbc.queryForObject(SELECT_BY_ID, paramsMap, BeanPropertyRowMapper.newInstance(Todo.class));
	}

	public Integer update(Todo todo) {
		SqlParameterSource paramsSource = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(UPDATE_BY_ID, paramsSource);
	}

	public Integer deleteById(Integer id) {
		Map<String, ?> paramsMap = Collections.singletonMap("id", id);
		return jdbc.update(DELETE_BY_ID, paramsMap);
	}

}
