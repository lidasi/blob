package com.example.hitest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Title implements Serializable {

    private static final long serialVersionUID = -6570402708082520531L;

    private Long title_id;

    private String title_content;

    private String update_time;

    private String create_time;

    public Title(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getTitle_id() {
        return title_id;
    }

    public void setTitle_id(Long title_id) {
        this.title_id = title_id;
    }

    public String getTitle_content() {
        return title_content;
    }

    public void setTitle_content(String title_content) {
        this.title_content = title_content;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
