package com.example.zhihu.service.impl;

import com.example.zhihu.dao.CommentDAO;
import com.example.zhihu.dataobject.CommentDO;
import com.example.zhihu.model.Comment;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.CommentService;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Result<Comment> post(String refId, long userId,long parentId, String content) {
        Result<Comment> result = new Result<>();
        //  先判断参数
        if (StringUtils.isEmpty(refId) || userId == 0 || StringUtils.isEmpty(content)){
            result.setCode("500");
            result.setMessage("refId、userId、content 不能为空");
            return result;
        }

        //  避免恶意输入html内容
        String body = StringEscapeUtils.escapeHtml4(content);

        CommentDO commentDO = new CommentDO();
        commentDO.setUserId(userId);
        commentDO.setContent(body);
        commentDO.setRefId(refId);
        commentDO.setParentId(parentId);
        commentDAO.add(commentDO);

        result.setData(commentDO.convertToModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Comment>> query(String refId) {
        Result<List<Comment>> result = new Result<>();
        //  先查询出所有评论
        List<Comment> comments = commentDAO.findByRefId(refId);
        //  构建Map结构
        Map<Long,Comment> map = new HashMap<>();
        //  创建一个一级节点
        map.put(0L,new Comment());
        //  将所有数据都存储进去
        comments.forEach(comment -> {map.put(comment.getId(),comment);});
        //  再次遍历构件父子关系
        comments.forEach(comment -> {
            //  得到父评论
            Comment parent = map.get(comment.getParentId());
            if (parent != null){
                //  得到该父评论的子评论列表
                List<Comment> children = parent.getChildren();
                if (children == null){
                    children = new ArrayList<>();
                }
                children.add(comment);
                parent.setChildren(children);
            }
        });
        //  得到所有的一级评论
        List<Comment> commentList = map.get(0L).getChildren();

        result.setData(commentList);
        result.setSuccess(true);

        return result;
    }
}
