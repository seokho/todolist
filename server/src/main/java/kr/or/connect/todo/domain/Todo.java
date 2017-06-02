package kr.or.connect.todo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ODOL on 2017. 5. 30..
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Todo {
    private Integer id;
    private String title;
    private Integer status;
    private Date created;
    private Date modified;
}
