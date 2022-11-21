package com.example.zhihu.service.impl;

import com.example.zhihu.dao.ContentDAO;
import com.example.zhihu.dao.UserDAO;
import com.example.zhihu.dataobject.ContentDO;
import com.example.zhihu.dataobject.UserDO;
import com.example.zhihu.model.Content;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.ContentService;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDAO contentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedissonClient redisson;

    @Override
    public Result<Content> post(ContentDO contentDO) {
        Result<Content> result = new Result<>();

        if (contentDO == null){
            result.setCode("500");
            result.setMessage("内容对线不能为空");
            return result;
        }
        //  封装id
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String date = localDateTime.format(df2);
        RAtomicLong aLong = redisson.getAtomicLong("myContentPartNum"+date);
        aLong.expire(10, TimeUnit.SECONDS);
        long count = aLong.incrementAndGet();
        String id = date + count;
        contentDO.setId(id);
        contentDAO.add(contentDO);

        //  封装数据
        result.setSuccess(true);
        result.setData(contentDO.convertToModel());
        return result;
    }

    @Override
    public Result<List<Content>> search(String keyWord) {
        Result<List<Content>> result = new Result<>();
        List<ContentDO> hotTopics= contentDAO.findByTitle(keyWord);

        List<Content> list = new ArrayList<>();
        //  转化为模型
        hotTopics.forEach(hotTopic->{
            Content content = hotTopic.convertToModel();
            UserDO user = userDAO.findByUserId(hotTopic.getUserId());
            content.setUser(user.convertToModel());
            list.add(content);
        });

        result.setSuccess(true);
        result.setData(list);
        return result;
    }
}
