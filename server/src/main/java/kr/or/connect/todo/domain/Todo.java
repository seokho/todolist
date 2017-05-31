package kr.or.connect.todo.domain;

import kr.or.connect.todo.global.TaskState;

import java.util.Date;

/**
 * Created by ODOL on 2017. 5. 30..
 */
public class Todo {
    private Integer id;
    private String title;
    private String decs;
    private Integer task;

    private Date creatAt;
    private Date modifiedAt;

    public Todo() {

    }

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Todo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", decs='").append(decs).append('\'');
        sb.append(", task=").append(task);
        sb.append(", creatAt=").append(creatAt);
        sb.append(", modifiedAt=").append(modifiedAt);
        sb.append('}');
        return sb.toString();
    }
}
