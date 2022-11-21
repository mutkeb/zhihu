package com.example.zhihu.dataobject;

import com.example.zhihu.model.Content;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class ContentDO {

    private String id;

    private String title;

    private String detail;

    private String media;

    private int favor;

    private long userId;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
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

    public ContentDO(){

    }
    ContentDO(Content content){
        BeanUtils.copyProperties(content,this);
    }

    public Content convertToModel(){
        Content content = new Content();
        BeanUtils.copyProperties(this,content);
        return content;
    }
}
