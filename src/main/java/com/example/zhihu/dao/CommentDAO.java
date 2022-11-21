package com.example.zhihu.dao;

import com.example.zhihu.dataobject.CommentDO;
import com.example.zhihu.dataobject.UserDO;
import com.example.zhihu.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDAO {

    /**
     * 增加一个评论
     */
    int add(CommentDO commentDO);

    /**
     *更新评论
     */
    int update(CommentDO commentDO);

    /**
     *删除评论
     */
    int delete(@Param("id") long id);

    /**
     * 根据使用者id查找评论
     */
    List<CommentDO> findByUserIds(@Param("userIds") List<Long> ids);

    /**
     * 根据评论文章的id查找评论
     */
    List<Comment> findByRefId(@Param("refId") String refId);

    /**
     *查找所有评论
     */
    List<CommentDO> findAll();

    /**
     * 加入一连串评论，主要用于初始化数据
     */
    int batchAdd(@Param("list") List<CommentDO> commentDOS);
}
