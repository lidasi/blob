package com.example.hitest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 5065788616196496700L;

    private Long user_id;
    private String user_name;
    private String user_password;
    private String email;
    private Long email_code;
    private String create_time;
    private String updata_time;

    public Userinfo(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Long getEmail_code() {
        return email_code;
    }

    public void setEmail_code(Long email_code) {
        this.email_code = email_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdata_time() {
        return updata_time;
    }

    public void setUpdata_time(String updata_time) {
        this.updata_time = updata_time;
    }
}
