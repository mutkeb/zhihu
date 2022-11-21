package com.example.zhihu.service;

import com.example.zhihu.dataobject.ContentDO;
import com.example.zhihu.model.Content;
import com.example.zhihu.model.Result;

import java.time.LocalDateTime;
import java.util.List;

public interface ContentService {

    /**
     * 发布内容
     *
     * @param contentDO
     * @return
     */
    public Result<Content> post(ContentDO contentDO);



    /**
     * 根据关键字搜索内容
     * @param keyWord
     * @return
     */
    public Result<List<Content>> search(String keyWord);


}
