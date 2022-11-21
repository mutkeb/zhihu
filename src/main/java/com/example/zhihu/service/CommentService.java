package com.example.zhihu.service;

import com.example.zhihu.model.Comment;
import com.example.zhihu.model.Result;

import java.util.List;

public interface CommentService {


    /**
     *发布评论
     *
     * @param refId
     * @param userId
     * @param content
     * @param parentId
     * @return
     */
    public Result<Comment> post(String refId,long userId,long parentId,String content);


    /**
     *查询评论
     *
     * @param refId
     * @return
     */
    public Result<List<Comment>> query(String refId);
}

