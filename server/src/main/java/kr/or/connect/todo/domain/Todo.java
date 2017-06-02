package kr.or.connect.todo.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by ODOL on 2017. 5. 30..
 */
@Data
public class Todo {
    private Integer id;
    private String title;
    private Integer status;

    private Date created;
    private Date modified;

    public Todo() {

    }
}
