package kr.or.connect.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by ODOL on 2017. 6. 3..
 */

@AllArgsConstructor
@Getter
public enum TodoStatus {
    ACTIVE_TODO("0"),
    COMPLETED_TODO("1"),
    REMOVED_TODO("2");

    private String code;
}
