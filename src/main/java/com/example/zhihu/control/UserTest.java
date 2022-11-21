package com.example.zhihu.control;


import com.example.zhihu.dao.UserDAO;
import com.example.zhihu.dataobject.UserDO;
import com.example.zhihu.model.Result;
import com.example.zhihu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/test/user")
public class UserTest {

    @Autowired
    private UserDAO userDAO;


    //  添加测试
    @GetMapping("/add")
    @ResponseBody
    public List<UserDO> add(){
        UserDO userDO = new UserDO();
        userDO.setUserName("mutkeb");
        userDO.setPwd("123456");
        userDO.setMobile("15555123123");
        userDO.setAvatar("/img/avatar.png");
        userDO.setEmail("1231122@qq.com");
        userDAO.add(userDO);
        return userDAO.findAll();
    }

    //  更新测试
    @GetMapping("/update")
    @ResponseBody
    public List<UserDO> update(){
        UserDO userDO = new UserDO();
        userDO.setId(Long.parseLong("1"));
        userDO.setAvatar("2");
        userDO.setUserName("aa");
        userDO.setMobile("11123");
        userDAO.update(userDO);
        return userDAO.findAll();
    }

    //  删除测试
    @GetMapping("/delete")
    @ResponseBody
    public List<UserDO> delete(){
        userDAO.delete(5);
        return userDAO.findAll();
    }

    //  根据用户姓名找用户
    @GetMapping("/searchByUserName")
    @ResponseBody
    public UserDO searchByUserName(){
        return userDAO.findByUserName("mutkeb");
    }

    //  加入一连串用户
    @GetMapping("/batchAdd")
    @ResponseBody
    public List<UserDO> batchAdd(){
        List<UserDO> list = new ArrayList<>();
        //  加入用户1
        UserDO userDO1 = new UserDO();
        userDO1.setUserName("Nux Lin");
        userDO1.setPwd("123456");
        userDO1.setMobile("16388111211");
        userDO1.setAvatar("/img/avatar1.png");
        list.add(userDO1);
        //  加入用户2
        UserDO userDO2 = new UserDO();
        userDO2.setUserName("高广银");
        userDO2.setPwd("123456");
        userDO2.setMobile("183881167211");
        userDO2.setAvatar("/img/avatar2.png");
        list.add(userDO2);
        //  加入用户3
        UserDO userDO3 = new UserDO();
        userDO3.setUserName("济南猴哥说房");
        userDO3.setPwd("123456");
        userDO3.setMobile("15388111255");
        userDO3.setAvatar("/img/avatar3.png");
        list.add(userDO3);
        //  加入用户4
        UserDO userDO4 = new UserDO();
        userDO4.setUserName("张宏波");
        userDO4.setPwd("123456");
        userDO4.setMobile("18382211671");
        userDO4.setAvatar("/img/avatar4.png");
        list.add(userDO4);
        //  加入用户5
        UserDO userDO5 = new UserDO();
        userDO5.setUserName("Tony Dynn");
        userDO5.setPwd("123456");
        userDO5.setMobile("18389111321");
        userDO5.setAvatar("/img/avatar5.png");
        list.add(userDO5);
        //  加入用户6
        UserDO userDO6 = new UserDO();
        userDO6.setUserName("倾城一顾");
        userDO6.setPwd("123456");
        userDO6.setEmail("163324@qq.com");
        userDO6.setAvatar("/img/avatar6.png");
        list.add(userDO6);
        //  加入用户7
        UserDO userDO7 = new UserDO();
        userDO7.setUserName("无聊");
        userDO7.setPwd("123456");
        userDO7.setEmail("9118823@qq.com");
        userDO7.setAvatar("/img/avatar7.png");
        list.add(userDO7);
        //  加入用户8
        UserDO userDO8 = new UserDO();
        userDO8.setUserName("sunyang500");
        userDO8.setPwd("123456");
        userDO8.setEmail("999138882@qq.com");
        userDO8.setAvatar("/img/avatar8.png");
        list.add(userDO8);
        //  加入用户9
        UserDO userDO9 = new UserDO();
        userDO9.setUserName("创造神之戒");
        userDO9.setPwd("123456");
        userDO9.setAvatar("11829991@qq.com");
        userDO9.setAvatar("/img/avatar9.png");
        list.add(userDO9);
        //  加入用户10
        UserDO userDO10 = new UserDO();
        userDO10.setUserName("钱包包");
        userDO10.setPwd("123456");
        userDO10.setEmail("2991882@qq.com");
        userDO10.setAvatar("/img/avatar10.png");
        list.add(userDO10);
        userDAO.batchAdd(list);
        return userDAO.findAll();
    }

    //  测试查看所有用户
    @GetMapping("/findAll")
    @ResponseBody
    public List<UserDO> findAll(){
        return userDAO.findAll();
    }

    //  测试邮箱
    @GetMapping("/email")
    @ResponseBody
    public boolean isMobile(){
        return "1118@qq.com".contains("@");
    }
}
