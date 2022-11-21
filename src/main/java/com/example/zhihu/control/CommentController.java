package com.example.zhihu.control;


import com.example.zhihu.dao.CommentDAO;
import com.example.zhihu.dataobject.ContentDO;
import com.example.zhihu.model.Comment;
import com.example.zhihu.model.Content;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //  查询文章的评论
    @PostMapping("/query")
    @ResponseBody
    public Result<List<Comment>> query(@RequestParam("refId") String refId){
        return commentService.query(refId);
    }

    //  发布评论
    @PostMapping("/post")
    @ResponseBody
    public Result<Comment> post(@RequestParam("refId") String refId,
                                @RequestParam("userId") long userId,
                                @RequestParam("parentId") long parentId,
                                @RequestParam("content") String content){
        return commentService.post(refId, userId, parentId, content);
    }

}
