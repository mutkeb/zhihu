package com.example.zhihu.service.impl;

import com.example.zhihu.dao.UserDAO;
import com.example.zhihu.dataobject.UserDO;
import com.example.zhihu.model.Result;
import com.example.zhihu.model.User;
import com.example.zhihu.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result<User> register(String text, String pwd) {
        Result result = new Result();
        UserDO userDO = new UserDO();
        //  对密码进行加密处理
        String saltPwd = pwd + "zhihu2022";
        //  生成md5值
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();
        userDO.setPwd(md5Pwd);
        //  初始用户名即为手机号
        userDO.setUserName(text);
        userDO.setAvatar("/img/avatar.png");
        if (isMobile(text)){
            //  输入的文本是手机，则进行手机验证码的检验
            userDO.setMobile(text);
        }else{
            //  输入的文本是邮箱，则进行邮箱验证码的检验
            userDO.setEmail(text);
        }
        userDAO.add(userDO);
        //  对数据进行封装
        result.setSuccess(true);
        result.setData(userDO.convertToModel());

        //  存储进入Redis数据库
        redisTemplate.opsForHash().put(CACHE_KEY,text,userDO);
        return result;
    }

    @Override
    public Result<User> login(String text, String pwd) {
        Result result = new Result();
        //  在数据库中搜索
        UserDO userDO = new UserDO();
        if (!isMobile(text)){
            userDO = userDAO.findByEmail(text);
        }else{
            userDO = userDAO.findByMobile(text);
        }

        if (!pwd.equals(userDO.getPwd())){
            //  密码不正确
            result.setCode("604");
            result.setMessage("密码不正确");
            return result;
        }
        //  成功登录
        result.setSuccess(true);
        result.setData(userDO.convertToModel());
        return result;
    }
    @Override
    public boolean isMobile(String text) {
        if (text.contains("@")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Result<User> registerOrLogin(String text, String pwd) {
        Result result = new Result();
        if (StringUtils.isEmpty(text)){
            result.setMessage("手机号/邮箱不能为空");
            result.setCode("600");
            return result;
        }
        if (isMobile(text) && text.length() != 11){
            result.setMessage("手机号格式不正确");
            result.setCode("601");
            return result;
        }

        if (StringUtils.isEmpty(pwd)){
            result.setMessage("密码不能为空");
            result.setCode("602");
            return result;
        }
        if (pwd.length() < 6){
            result.setMessage("密码长度不能小于6");
            result.setCode("603");
            return result;
        }

        //  通过查找数据库判断执行登录逻辑还是注册逻辑
        UserDO userDO = (UserDO)redisTemplate.opsForHash().get(CACHE_KEY,text);
        if (userDO == null){
            //  此时代表Redis数据库中不存在，再查找Mysql数据库
            if (isMobile(text)){
                //  手机号查找
                userDO = userDAO.findByMobile(text);
            }else{
                //  邮箱查找
                userDO = userDAO.findByEmail(text);
            }
        }
        if (userDO == null){
            //  此时执行注册逻辑
            return register(text,pwd);
        }
        //  执行登录逻辑
        return login(text,pwd);
    }

}
