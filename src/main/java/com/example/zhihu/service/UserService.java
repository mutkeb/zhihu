package com.example.zhihu.service;


import com.example.zhihu.model.Result;
import com.example.zhihu.model.User;

public interface UserService {

    String CACHE_KEY = "integralRankUser";

    String CACHE_MODEL_KEY = "integralRankUserModel";

    String CACHE_LOGIN_KEY = "zhihuLoginUser";

    /**
     *  用户注册逻辑
     *
     * @param text
     * @param pwd
     * @return
     */
    public Result<User> register(String text,String pwd);

    /**
     * 用户登录逻辑
     *
     * @param text
     * @param pwd
     */
     public Result<User> login(String text,String pwd);

    /**
     * 判断输入的文本是电话号码还是邮箱
     *
     * @param text
     * @return
     */
    public boolean isMobile(String text);

    /**
     * 用户注册/登录逻辑
     *
     * @param text
     * @param pwd
     */
    public Result<User> registerOrLogin(String text,String pwd);

    /**
     * 用户退出逻辑
     */

}
