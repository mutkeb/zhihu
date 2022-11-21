package com.example.zhihu.dataobject;

import com.example.zhihu.model.Comment;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class CommentDO {

    private long id;

    private String refId;

    private long userId;

    private String content;

    private long parentId;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    private int favor;

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public CommentDO(){

    }
    public Comment convertToModel(){
        Comment comment = new Comment();
        BeanUtils.copyProperties(this,comment);
        return comment;
    }

    CommentDO(Comment comment){
        BeanUtils.copyProperties(comment,this);
    }
}
