package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL =
			"SELECT id, title, desc, create_at, modified_at FROM todo";
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	static final String SELECT_BY_TASK =
			"SELECT id, title, desc, create_at, modified_at FROM todo WHERE task= :task";
	static final String SELECT_BY_ID =
			"SELECT id, title, desc, create_at, modified_at FROM todo WHERE id= :id";
//	static final String UPDATE_TASK =
//			"UPDATE todo SET task = :task WHERE id = :id";
	static final String UPDATE_BY_ID =
			"UPDATE todo SET\n"
					+ "title = :title,"
					+ "desc = :desc,"
					+ "task = :task,"
					+ "modified_at = :modified_at\n"
					+ "WHERE id = :id";
	static final String COUNT_TODO =
			"SELECT COUNT(*) FROM todo";
}
