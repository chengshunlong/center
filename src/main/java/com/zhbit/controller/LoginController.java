package com.zhbit.controller;

import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.Users;
import com.zhbit.dao.UserMapper;
import com.zhbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-10-8:57
 */
@RequestMapping("/login")
@Controller
@Transactional
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(Users users, HttpSession session, Model model){
        //如果数据库中存在当前的userid且密码正确，则添加到session域中，否则返回登陆失败
        Users user = userService.queryUserByUserId(users.getUserId());
        System.out.println(user);
        if((user != null) && (user.getUserPwd().equals(users.getUserPwd()))){
            session.setAttribute("user", user);
            //判断用户类型
            if(user.getUserType().equals("admin")){
                return "adminIndex";
            }else{
                return "userIndex";
            }
        }
        model.addAttribute("msg", "用户名或密码错误");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("shoppingCart");
        return "userIndex";
    }


    @RequestMapping("/loginVerify")
    @ResponseBody
    public String LoginVerify(String userId,String userPwd){
        String msg = "";
        //如果数据库中存在当前的userid且密码正确
        Users users = userService.queryUserByUserId(userId);
        String password = userService.queryUserPwdByUserId(userId);

        if(userId.length() > 16 || userId.length() < 6){
            msg = "账号长度6-16位";
        }else{
            if(BeanUtils.isSpecialChar(userId) == false){
                if(users != null){
                    if(users.getUserPwd().equals(userPwd)){
                        msg = "密码正确！";
                    }else{
                        msg = "密码错误！";
                    }
                }else{
                    msg = "账号不存在！";
                }
            }else{
                msg = "账号必须为数字、英文或数字英文组合";
            }
        }
        return msg;
    }

    @RequestMapping("/toUserIndex")
    public String toUserIndex(){
        return "userIndex";
    }

    @RequestMapping("/toAdminIndex")
    public String toAdminIndex(){
        return "adminIndex";
    }
}

