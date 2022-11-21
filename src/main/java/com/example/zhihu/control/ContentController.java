package com.example.zhihu.control;


import com.example.zhihu.dao.ContentDAO;
import com.example.zhihu.dataobject.ContentDO;
import com.example.zhihu.model.Content;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentDAO contentDAO;

    //  搜索
    @PostMapping(path = "/searchContent")
    @ResponseBody
    public Result<List<Content>> searchContent(@RequestParam("keyword") String keyword){
        return contentService.search(keyword);
    }

    //  渲染文章内容
    @GetMapping(path = "/skip")
    public String skip(@RequestParam("refId") String refId, Model model){
        ContentDO contentDO = contentDAO.findByRefId(refId);
        model.addAttribute("content",contentDO.convertToModel());
        return "content";
    }

    //  点赞文章
    @PostMapping("path=/good")
    public Result<Content> good(@RequestParam("refId")String refId){
        ContentDO contentDO = contentDAO.findByRefId(refId);
        contentDO.setFavor(contentDO.getFavor() + 1);
        contentDAO.update(contentDO);
        Result<Content> result = new Result<>();
        result.setData(contentDO.convertToModel());
        return result;
    }
}
