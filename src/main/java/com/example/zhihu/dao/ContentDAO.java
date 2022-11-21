package com.example.zhihu.dao;

import com.example.zhihu.dataobject.ContentDO;
import com.example.zhihu.dataobject.UserDO;
import com.example.zhihu.model.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContentDAO {
    /**
     * 增加一个文章
     */
    int add(ContentDO contentDO);

    /**
     *更新文章
     */
    int update(ContentDO contentDO);

    /**
     *删除文章
     */
    int delete(@Param("id") String id);

    /**
     * 根据内容标题找文章，这里是模糊查询
     */
    List<ContentDO> findByTitle(@Param("title") String title);

    /**
     *查找所有文章
     */
    List<ContentDO> findAll();

    /**
     * 根据文章ID寻找文章
     */
    ContentDO findByRefId(@Param("refId") String refId);

//    /**
//     * 加入一连串文章，主要用于初始化数据
//     */
//    int batchAdd(@Param("list") List<ContentDO> contentDOS);



}
