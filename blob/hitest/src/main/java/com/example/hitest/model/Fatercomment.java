package com.example.hitest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Fatercomment implements Serializable {

    private static final long serialVersionUID = 5205292984119492366L;
    private Long seq;
    private String comment_info;
    private Long pid;
    private Long userinfo_id;
    private Long article_id;
    private String comment_time;

    public Fatercomment(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getComment_info() {
        return comment_info;
    }

    public void setComment_info(String comment_info) {
        this.comment_info = comment_info;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUserinfo_id() {
        return userinfo_id;
    }

    public void setUserinfo_id(Long userinfo_id) {
        this.userinfo_id = userinfo_id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }
}
