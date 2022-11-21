package com.example.zhihu.control;

import com.example.zhihu.dao.UserDAO;
import com.example.zhihu.dataobject.UserDO;
import com.example.zhihu.model.Result;
import com.example.zhihu.model.User;
import com.example.zhihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;


    //  用户注册/登录
    @PostMapping("/login")
    public String login(@RequestParam("text") String text, @RequestParam("pwd") String pwd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result<User> userResult = userService.registerOrLogin(text, pwd);
        //  将用户Id存在session里
        if (userResult.isSuccess()){
            request.getSession().setAttribute("userId",userResult.getData().getId());
        }
        request.getSession().setAttribute("symbol",1);
        return "main";
    }

    //  用户退出
    @GetMapping("/logout")
    @ResponseBody
    public Result<User> logout(HttpServletRequest request){
        Result<User> result = new Result<>();
        request.getSession().removeAttribute("userId");
        result.setSuccess(true);
        return result;
    }

    //  根据用户Id找人
    @GetMapping("findById")
    @ResponseBody
    public Result<User> findById(@RequestParam("id") long id){
        Result<User> result = new Result<>();
        UserDO userDO = userDAO.findByUserId(id);
        result.setData(userDO.convertToModel());
        return result;
    }

    //  显示用户头像
    @PostMapping("/avatar")
    @ResponseBody
    public Result<User> getAvatar(HttpServletRequest request){
        Result<User> result = new Result<>();
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null && session.getAttribute("symbol") != null && (int)session.getAttribute("symbol") == 1){
            result.setSymbol(1);
            return result;
        }else if (session.getAttribute("userId") == null){
            return result;
        }
        request.getSession().setAttribute("symbol",0);
        long userId = (long) session.getAttribute("userId");
        UserDO userDO = userDAO.findByUserId(userId);
        result.setData(userDO.convertToModel());
        result.setSuccess(true);
        return result;
    }
}
