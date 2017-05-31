package kr.or.connect.todo.domain;

import kr.or.connect.todo.global.TaskState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ODOL on 2017. 5. 30..
 */
@Data
public class Todo {
    private Integer id;
    private String title;
    private String desc;
    private Integer task;

    private Date created;
    private Date modified;

    public Todo() {

    }
}
