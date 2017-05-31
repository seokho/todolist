CREATE TABLE todo (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	title TEXT,
  desc TEXT,
	task INT(2) NOT NULL DEFAULT 0,
	created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);