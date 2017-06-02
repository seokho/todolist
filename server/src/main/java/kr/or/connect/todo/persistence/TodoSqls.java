package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL =
			"SELECT id, title, created, modified FROM todo";
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	static final String SELECT_BY_STATUS =
			"SELECT id, title, created, modified FROM todo WHERE status= :status ORDER BY modified DESC";
	static final String SELECT_BY_ID =
			"SELECT id, title, created, modified FROM todo WHERE id= :id";
	static final String UPDATE_BY_ID =
			"UPDATE todo SET\n"
					+ "status = :status "
					+ "WHERE id = :id";
	static final String INSERT_TODO =
			"INSERT INTO todo(title, status) VALUES (:title, :status)";
}
