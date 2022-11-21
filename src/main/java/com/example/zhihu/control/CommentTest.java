package com.example.zhihu.control;

import com.example.zhihu.dao.CommentDAO;
import com.example.zhihu.dataobject.CommentDO;
import com.example.zhihu.dataobject.ContentDO;
import com.example.zhihu.model.Comment;
import com.example.zhihu.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/test/comment")
public class CommentTest {

    @Autowired
    private CommentDAO commentDAO;

    //  添加测试
    @GetMapping("/add")
    @ResponseBody
    public List<CommentDO> add(){
        CommentDO commentDO = new CommentDO();
        commentDO.setRefId("2");
        commentDO.setUserId(1L);
        commentDO.setContent("评论1");
        commentDO.setParentId(0);
        commentDAO.add(commentDO);
        return commentDAO.findAll();
    }

    //  更新测试
    @GetMapping("/update")
    @ResponseBody
    public List<CommentDO> update(){
        CommentDO commentDO = new CommentDO();
        commentDO.setId(1);
        commentDO.setContent("评论2");
        commentDAO.update(commentDO);
        return commentDAO.findAll();
    }

    //  删除测试
    @GetMapping("/delete")
    @ResponseBody
    public List<CommentDO> delete(){
        commentDAO.delete(12);
        return commentDAO.findAll();
    }

    //  根据用户ID找评论
    @GetMapping("/findByUserIds")
    @ResponseBody
    public List<CommentDO> findByUserIds(){
        List<Long> ids = new ArrayList<>();
        ids.add((long) 2);
        ids.add((long) 3);
        return commentDAO.findByUserIds(ids);
    }

    //  根据文章ID找评论
    @GetMapping("/findByRefId")
    @ResponseBody
    public List<Comment> findByRefId(){
        return commentDAO.findByRefId("202211031410421741");
    }

    //  加入一连串评论
    @GetMapping("/batchAdd")
    @ResponseBody
    public List<CommentDO> batchAdd(){
        List<CommentDO> list = new ArrayList<>();
        //  加入评论1
        CommentDO commentDO = new CommentDO();
        commentDO.setRefId("202211031410429511");
        commentDO.setUserId(17);
        commentDO.setContent("评论1");
        list.add(commentDO);
        //  加入评论2
        CommentDO commentDO2 = new CommentDO();
        commentDO2.setRefId("202211031410421741");
        commentDO2.setUserId(17);
        commentDO2.setContent("评论2");
        list.add(commentDO2);
        //  加入评论3
        CommentDO commentDO3 = new CommentDO();
        commentDO3.setRefId("202211031410408241");
        commentDO3.setUserId(18);
        commentDO3.setContent("评论3");
        list.add(commentDO3);
        //  加入评论4
        CommentDO commentDO4 = new CommentDO();
        commentDO4.setRefId("202211031410404121");
        commentDO4.setUserId(18);
        commentDO4.setContent("评论4");
        list.add(commentDO4);
        //  加入评论5
        CommentDO commentDO5 = new CommentDO();
        commentDO5.setRefId("202211031410401731");
        commentDO5.setUserId(19);
        commentDO5.setContent("评论5");
        list.add(commentDO5);
        //  加入评论6
        CommentDO commentDO6 = new CommentDO();
        commentDO6.setRefId("202211031410406581");
        commentDO6.setUserId(19);
        commentDO6.setContent("评论6");
        list.add(commentDO6);
        //  加入评论7
        CommentDO commentDO7 = new CommentDO();
        commentDO7.setRefId("202211031410425021");
        commentDO7.setUserId(20);
        commentDO7.setContent("评论7");
        list.add(commentDO7);
        //  加入评论8
        CommentDO commentDO8 = new CommentDO();
        commentDO8.setRefId("202211031410391951");
        commentDO8.setUserId(20);
        commentDO8.setContent("评论8");
        list.add(commentDO8);
        //  加入评论9
        CommentDO commentDO9 = new CommentDO();
        commentDO9.setRefId("202211031410418031");
        commentDO9.setUserId(21);
        commentDO9.setContent("评论9");
        list.add(commentDO9);
        //  加入评论10
        CommentDO commentDO10 = new CommentDO();
        commentDO10.setRefId("202211031410428101");
        commentDO10.setUserId(21);
        commentDO10.setContent("评论10");
        list.add(commentDO10);
        //  加入评论11
        CommentDO commentDO11 = new CommentDO();
        commentDO11.setRefId("202211031410415541");
        commentDO11.setUserId(22);
        commentDO11.setContent("评论11");
        list.add(commentDO11);
        //  加入评论12
        CommentDO commentDO12 = new CommentDO();
        commentDO12.setRefId("202211031410400051");
        commentDO12.setUserId(22);
        commentDO12.setContent("评论12");
        list.add(commentDO2);
        //  加入评论13
        CommentDO commentDO13 = new CommentDO();
        commentDO13.setRefId("202211031410394841");
        commentDO13.setUserId(23);
        commentDO13.setContent("评论13");
        list.add(commentDO13);
        //  加入评论14
        CommentDO commentDO14 = new CommentDO();
        commentDO14.setRefId("202211031410426481");
        commentDO14.setUserId(23);
        commentDO14.setContent("评论14");
        list.add(commentDO14);
        //  加入评论15
        CommentDO commentDO15 = new CommentDO();
        commentDO15.setRefId("202211031410398091");
        commentDO15.setUserId(24);
        commentDO15.setContent("评论15");
        list.add(commentDO15);
        //  加入评论16
        CommentDO commentDO16 = new CommentDO();
        commentDO16.setRefId("202211031410410311");
        commentDO16.setUserId(24);
        commentDO16.setContent("评论16");
        list.add(commentDO16);
        //  加入评论17
        CommentDO commentDO17 = new CommentDO();
        commentDO17.setRefId("202211031410423381");
        commentDO17.setUserId(25);
        commentDO17.setContent("评论17");
        list.add(commentDO17);
        //  加入评论18
        CommentDO commentDO18 = new CommentDO();
        commentDO18.setRefId("202211031410396511");
        commentDO18.setUserId(25);
        commentDO18.setContent("评论18");
        list.add(commentDO18);
        //  加入评论19
        CommentDO commentDO19 = new CommentDO();
        commentDO19.setRefId("202211031410387381");
        commentDO19.setUserId(26);
        commentDO19.setContent("评论19");
        list.add(commentDO19);
        //  加入评论20
        CommentDO commentDO20 = new CommentDO();
        commentDO20.setRefId("202211031410412301");
        commentDO20.setUserId(26);
        commentDO20.setContent("评论20");
        list.add(commentDO20);
        commentDAO.batchAdd(list);
        return commentDAO.findAll();
    }

    //  测试查看所有用户
    @GetMapping("/findAll")
    @ResponseBody
    public List<CommentDO> findAll(){
        return commentDAO.findAll();
    }
}
